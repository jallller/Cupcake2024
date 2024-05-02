package app.persistence;

import app.entities.Order;
import app.entities.OrderLine;
import app.entities.User;
import app.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper
{



    public static List<Order> getAllOrdersPerUser(int orderId, ConnectionPool connectionPool) throws DatabaseException
    {
        List<Order> orderList = new ArrayList<>();
        String sql = "select * from orders where orders_id=? order by price";

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

    public static List<OrderLine> addOrder(int userId, ConnectionPool connectionPool) throws DatabaseException
    {
        List<OrderLine> orderList = new ArrayList<>();
        String sql = "insert into orderline (orderline_Id,orders_id,cupcaketop_Id, cupcakebottom_Id,quantity) values (?,?,?,?,?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("orderline_Id");
                int orders_id = rs.getInt("orders_id");
                int cupcaketop_id = rs.getInt("cupcaketop_Id");
                int cupcakebottom_id = rs.getInt("cupcakebottom_Id");
                int quantity = rs.getInt("quantity");
                orderList.add(new OrderLine(id, orders_id,cupcaketop_id,cupcakebottom_id,quantity));
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return orderList;
    }

    public static OrderLine addTask(User user, int orderline_Id, ConnectionPool connectionPool) throws DatabaseException
    {
        OrderLine newOrder = null;

        String sql = "insert into orderline (orderline_Id,orders_id,cupcaketop_Id,cupcakebottom_Id, quantity) values (?,?,?,?,?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        )
        {
            ps.setString(1, "orderline_Id");
            ps.setString(2, "orders_id");
            ps.setString(3, "cupcaketop_Id");
            ps.setString(4, "cupcakebottom_Id");
            ps.setString(5, "quantity");
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int newId = rs.getInt(1);
//                newOrder = new OrderLine(newId,orders_id,cupcaketop_Id,cupcakebottom_Id,quantity);
            } else
            {
                throw new DatabaseException("Fejl under indsætning af task: " + newOrder);
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Fejl i DB connection", e.getMessage());
        }
        return newOrder;


    }

//    public static void createOrder(int orders_id, int user_Id, int price, ConnectionPool connectionPool) throws DatabaseException {
//        String sql = "insert into orders (orders_id,user_Id,price) values (?,?,?)";
//
//        try (
//                Connection connection = connectionPool.getConnection();
//                PreparedStatement ps = connection.prepareStatement(sql)
//        ) {
//            ps.setInt(1, orders_id);
//            ps.setInt(2, user_Id);
//            ps.setInt(3, price);
//
//
//            int rowsAffected = ps.executeUpdate();
//            if (rowsAffected != 1) {
//                throw new DatabaseException("Fejl ved oprettelse af ny order");
//            }
//        } catch (SQLException e) {
//            String msg = "Der er sket en fejl. Prøv igen";
//            if (e.getMessage().startsWith("ERROR: duplicate key value ")) {
//                msg = "Brugernavnet findes allerede. Vælg et andet";
//            }
//            throw new DatabaseException(msg, e.getMessage());
//        }
//    }

    public static void createorder(int user_Id, int price, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "insert into users (users_Id,price) values (?,?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, user_Id);
            ps.setInt(2, price);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl ved oprettelse af ny bruger");
            }
        } catch (SQLException e) {
            String msg = "Der er sket en fejl. Prøv igen";
            throw new DatabaseException(msg, e.getMessage());
        }
    }

}