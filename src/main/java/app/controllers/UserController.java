package app.controllers;

import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class UserController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("login", ctx -> login(ctx, connectionPool));
    }

    public static void login(Context ctx, ConnectionPool connectionPool) {
        //Hent formparametre
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        //Tjek om bruger findes i DB
        try {
            User user = UserMapper.login(username, password, connectionPool);
            //Hvis ja send videre til orderside
            ctx.render("order.html");
        } catch (DatabaseException e) {
            //Hvis nej send tilbage til loginside med fejlbesked
            ctx.attribute("message",e.getMessage());
            ctx.render("index.html");
        }


    }
}
