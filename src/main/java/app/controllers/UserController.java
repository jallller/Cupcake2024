package app.controllers;

import app.entities.Order;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.OrderMapper;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class UserController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("login", ctx -> login(ctx, connectionPool));
        app.get("logout", ctx -> logout(ctx));
    }

    private static void logout(Context ctx) {
        ctx.req().getSession().invalidate();
        ctx.redirect("/");
    }

    public static void login(Context ctx, ConnectionPool connectionPool) {
        //Hent formparametre
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        //Tjek om bruger findes i DB
        try {
            User user = UserMapper.login(username, password, connectionPool);
            ctx.sessionAttribute("currentUser", user);
            //Hvis ja send videre til orderside
            List<Order> orderList = OrderMapper.getAllOrdersPerUser(user.getUserId(),connectionPool);
            ctx.attribute("orderList",orderList);
            ctx.render("order.html");
        } catch (DatabaseException e) {
            //Hvis nej send tilbage til loginside med fejlbesked
            ctx.attribute("message",e.getMessage());
            ctx.render("index.html");
        }


    }
}
