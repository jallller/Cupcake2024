package app.controllers;

import app.entities.OrderLine;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.OrderMapper;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class OrderController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {


        app.get("addOrder", ctx -> addOrder(ctx, connectionPool));


    }

    public static void addOrder(Context ctx, ConnectionPool connectionPool) {
        int order = Integer.parseInt(ctx.formParam("order"));

        User user = ctx.sessionAttribute("currentUser");
        try {
            OrderLine orders = OrderMapper.addTask(user, order, connectionPool);
            List<OrderLine> orderList = OrderMapper.addOrder(user.getUserId(), connectionPool);
            ctx.attribute("orderList", orderList);
            ctx.render("order.html");
        } catch (DatabaseException e) {
            ctx.attribute("message","fejl....");
            ctx.render("order.html");
        }
    }

    private static void createOrder(Context ctx, ConnectionPool connectionPool) {
        //Hent formparametre
        int id = Integer.parseInt(ctx.formParam("orderline_Id"));
        int orders_id = Integer.parseInt(ctx.formParam("orders_id"));
        int cupcaketop_Id = Integer.parseInt(ctx.formParam("cupcaketop_Id"));
        int cupcakebottom_id = Integer.parseInt(ctx.formParam("cupcakebottom_Id"));
        int quantity = Integer.parseInt(ctx.formParam("quantity"));

        try {
            OrderMapper.addOrder(id, connectionPool);
            ctx.attribute("message", "indsat ordre");
            ctx.render("index.html");

        } catch (DatabaseException e) {
            ctx.attribute("message", "Fejl");
            ctx.render("order.html");

        }


    }
}
