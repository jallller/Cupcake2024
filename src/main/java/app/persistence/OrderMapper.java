package app.persistence;

import app.entities.Order;
import app.entities.User;
import app.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper
{

    //ORDERLINE

    public static List<Order> getAllOrdersPerUser(int orderId, ConnectionPool connectionPool) throws DatabaseException
    {
        List<Order> orderList = new ArrayList<>();
        String sql = "select * from orders where orders_Id=? order by price";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("orders_id");
                int user_Id = rs.getInt("user_Id");
                int price = rs.getInt("price");
                orderList.add(new Order(id, user_Id, price));
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return orderList;
    }

//    public static Order addOrder(User user, String orderName, ConnectionPool connectionPool) throws DatabaseException
//    {
//        Order newOrder = null;
//
//        String sql = "insert into orders (orders_id, user_id,price) values (?,?,?)";
//
//        try (
//                Connection connection = connectionPool.getConnection();
//                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
//        )
//        {
//            ps.setString(1, orderName);
//            ps.setBoolean(2, false);
//            ps.setInt(3, user.getUserId());
//            int rowsAffected = ps.executeUpdate();
//            if (rowsAffected == 1)
//            {
//                ResultSet rs = ps.getGeneratedKeys();
//                rs.next();
//                int newId = rs.getInt(1);
//                newOrder = new Order(newId, orderName,0, user.getUserId());
//            } else
//            {
//                throw new DatabaseException("Fejl under indsætning af task: " + orderName);
//            }
//        }
//        catch (SQLException e)
//        {
//            throw new DatabaseException("Fejl i DB connection", e.getMessage());
//        }
//        return newOrder;
//    }

//    public static void setDoneTo(boolean done, int taskId, ConnectionPool connectionPool) throws DatabaseException
//    {
//        String sql = "update task set done = ? where task_id = ?";
//
//        try (
//                Connection connection = connectionPool.getConnection();
//                PreparedStatement ps = connection.prepareStatement(sql)
//        )
//        {
//            ps.setBoolean(1, done);
//            ps.setInt(2, taskId);
//            int rowsAffected = ps.executeUpdate();
//            if (rowsAffected != 1)
//            {
//                throw new DatabaseException("Fejl i opdatering af en task");
//            }
//        }
//        catch (SQLException e)
//        {
//            throw new DatabaseException("Fejl i opdatering af en task");
//        }
//    }
//
//    public static void delete(int taskId, ConnectionPool connectionPool) throws DatabaseException
//    {
//        String sql = "delete from task where task_id = ?";
//
//        try (
//                Connection connection = connectionPool.getConnection();
//                PreparedStatement ps = connection.prepareStatement(sql)
//        )
//        {
//            ps.setInt(1, taskId);
//            int rowsAffected = ps.executeUpdate();
//            if (rowsAffected != 1)
//            {
//                throw new DatabaseException("Fejl i opdatering af en task");
//            }
//        }
//        catch (SQLException e)
//        {
//            throw new DatabaseException("Fejl ved sletning af en task", e.getMessage());
//        }
//    }
//
//    public static Task getTaskById(int taskId, ConnectionPool connectionPool) throws DatabaseException
//    {
//        Task task = null;
//
//        String sql = "select * from task where task_id = ?";
//
//        try (
//                Connection connection = connectionPool.getConnection();
//                PreparedStatement ps = connection.prepareStatement(sql)
//        )
//        {
//            ps.setInt(1, taskId);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next())
//            {
//                int id = rs.getInt("task_id");
//                String name = rs.getString("name");
//                Boolean done = rs.getBoolean("done");
//                int userId = rs.getInt("user_id");
//                task = new Task(id, name, done, userId);
//            }
//        }
//        catch (SQLException e)
//        {
//            throw new DatabaseException("Fejl ved hentning af task med id = " + taskId, e.getMessage());
//        }
//        return task;
//    }
//
//    public static void update(int taskId, String taskName, ConnectionPool connectionPool) throws DatabaseException
//    {
//        String sql = "update task set name = ? where task_id = ?";
//
//        try (
//                Connection connection = connectionPool.getConnection();
//                PreparedStatement ps = connection.prepareStatement(sql)
//        )
//        {
//            ps.setString(1, taskName);
//            ps.setInt(2, taskId);
//            int rowsAffected = ps.executeUpdate();
//            if (rowsAffected != 1)
//            {
//                throw new DatabaseException("Fejl i opdatering af en task");
//            }
//        }
//        catch (SQLException e)
//        {
//            throw new DatabaseException("Fejl i opdatering af en task", e.getMessage());
//        }
//    }
}