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

//
//        app.get("addOrder", ctx -> addOrder(ctx, connectionPool));
//        app.post("addOrder", ctx -> addOrder(ctx, connectionPool));
        app.get("createOrder", ctx -> createOrder(ctx, connectionPool));
        //app.post("createOrder", ctx -> createOrder(ctx, connectionPool));


    }

//    public static void addOrder(Context ctx, ConnectionPool connectionPool) {
//        int order = Integer.parseInt(ctx.formParam("orders_id"));
//
//        User user = ctx.sessionAttribute("currentUser");
//        try {
//            OrderLine orders = OrderMapper.addTask(user, order, connectionPool);
//            List<OrderLine> orderList = OrderMapper.addOrder(user.getUserId(), connectionPool);
//            ctx.attribute("orderList", orderList);
//            ctx.render("order.html");
//        } catch (DatabaseException e) {
//            ctx.attribute("message", "fejl....");
//            ctx.render("order.html");
//        }
//    }

//    private static void createOrder(Context ctx, ConnectionPool connectionPool) {
//        //Hent formparametre
//        int id = Integer.parseInt(ctx.formParam("orderline_Id"));
//        int orders_id = Integer.parseInt(ctx.formParam("orders_id"));
//        int cupcaketop_Id = Integer.parseInt(ctx.formParam("cupcaketop_Id"));
//        int cupcakebottom_id = Integer.parseInt(ctx.formParam("cupcakebottom_Id"));
//        int quantity = Integer.parseInt(ctx.formParam("quantity"));
//
//        try {
//            OrderMapper.addOrder(id, connectionPool);
//            ctx.attribute("message", "indsat ordre");
//            ctx.render("index.html");
//
//        } catch (DatabaseException e) {
//            ctx.attribute("message", "Fejl");
//            ctx.render("order.html");
//        }
//    }

//    private static void createUser(Context ctx, ConnectionPool connectionPool) {
//        //Hent formparametre
//        String username = ctx.formParam("username");
//        String password1 = ctx.formParam("password1");
//        String password2 = ctx.formParam("password2");
//        String role = ctx.formParam("role");
//        int balance = Integer.parseInt(ctx.formParam("balance"));
//
//        if (password1.equals(password2)) {
//            try {
//                UserMapper.createuser(username, password1, role, balance, connectionPool);
//                ctx.attribute("message", "Brugeren oprettet med brugernavn: " + username + ". Log venligst på");
//                ctx.render("index.html");
//
//            } catch (DatabaseException e) {
//                ctx.attribute("message", "Brugernavnet er allerede i brug");
//                ctx.render("createuser.html");
//            }
//
//        } else {
//            ctx.attribute("message", "Kodeordende matcher ikke");
//            ctx.render("createuser.html");
//        }
//
//
//    }


//    private static void createOrder(Context ctx, ConnectionPool connectionPool) {
//        //Hent formparametre
//        int orders_id = Integer.parseInt(ctx.formParam("orders_id"));
//        int user_Id = Integer.parseInt(ctx.formParam("user_Id"));
//        int price = Integer.parseInt(ctx.formParam("price"));
//
//
//        try {
//            OrderMapper.createOrder(orders_id, user_Id, price, connectionPool);
//            ctx.attribute("message", "Order oprettet for : " + user_Id);
//            ctx.render("order.html");
//
//        } catch (DatabaseException e) {
//            ctx.attribute("message", "Feeeejl");
//            ctx.render("order.html");
//        }
//    }

    private static void createOrder(Context ctx, ConnectionPool connectionPool) {
        //Hent formparametre
        int user_Id = Integer.parseInt(ctx.formParam("user_Id"));
        int price = Integer.parseInt(ctx.formParam("price"));

        try {
            OrderMapper.createorder(user_Id, price, connectionPool);
            ctx.attribute("message", "order for brugeren : " + user_Id + ". Oprettet");
            ctx.render("index.html");

        } catch (DatabaseException e) {
            ctx.attribute("message", "Sket en fejl");
            ctx.render("order.html");


        }
        ctx.attribute("message", "FEEEJL");
        ctx.render("order.html");
    }

}


