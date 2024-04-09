package app.persistence;

import app.entities.Order;
import app.entities.User;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper
{

    public static User login(String username, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "select * from users where username=? and password=?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                String role = rs.getString("role"); //*
                int id = rs.getInt("user_Id");
                return new User(id, username, password,role);
            } else
            {
                throw new DatabaseException("Fejl i login. Prøv igen");
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("DB fejl", e.getMessage());
        }
    }

    public static void createuser(String username, String password, String role, int balance, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "insert into users (username, password,role,balance) values (?,?,?,?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3,role);
            ps.setInt(4,balance);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl ved oprettelse af ny bruger");
            }
        } catch (SQLException e) {
            String msg = "Der er sket en fejl. Prøv igen";
            if (e.getMessage().startsWith("ERROR: duplicate key value ")) {
                msg = "Brugernavnet findes allerede. Vælg et andet";
            }
            throw new DatabaseException(msg, e.getMessage());
        }

    }
    public static List<User> getAllUsers(int user_Id, String username, String password, String role, int balance,  ConnectionPool connectionPool) throws DatabaseException
    {
        List<User> userList = new ArrayList<>();
        String sql = "select * from users where username=? order by username";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {

            ps.setInt(1, user_Id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("user_Id");
                username = rs.getString("username");
                password = rs.getString("password");
                role = rs.getString("role");
                balance = rs.getInt("balance");
                userList.add(new User(id, username,password,role));
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return userList;
    }
}