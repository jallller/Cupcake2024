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
//        app.get("goToInfo", ctx -> ctx.render("info.html"));
//        app.post("goToInfo", ctx -> goToInfo(ctx));
        app.get("createuser", ctx -> ctx.render("createuser.html"));
        app.post("createuser", ctx -> createUser(ctx, connectionPool));
        //app.post("getAllUsers", ctx -> getAllUsers(ctx, connectionPool));
    }

    private static void createUser(Context ctx, ConnectionPool connectionPool) {
        //Hent formparametre
        String username = ctx.formParam("username");
        String password1 = ctx.formParam("password1");
        String password2 = ctx.formParam("password2");
        String role = ctx.formParam("role");
        int balance = Integer.parseInt(ctx.formParam("balance"));

        if (password1.equals(password2)) {
            try {
                UserMapper.createuser(username, password1, role, balance, connectionPool);
                ctx.attribute("message", "Brugeren oprettet med brugernavn: " + username + ". Log venligst på");
                ctx.render("index.html");

            } catch (DatabaseException e) {
                ctx.attribute("message", "Brugernavnet er allerede i brug");
                ctx.render("createuser.html");
            }

        } else {
            ctx.attribute("message", "Kodeordende matcher ikke");
            ctx.render("createuser.html");
        }
    }

    private static void logout(Context ctx) {
        ctx.req().getSession().invalidate();
        ctx.redirect("/");
    }

//    //Link i menu
//    private static void goToInfo(Context ctx) {
//        //ctx.redirect("info.html");
//        ctx.render("info.html");
//    }

    public static void login(Context ctx, ConnectionPool connectionPool) {
        //Hent formparametre
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        //Tjek om bruger findes i DB
        try {

            User user = UserMapper.login(username, password, connectionPool);
            ctx.sessionAttribute("currentUser", user);
            //Hvis ja send videre til orderside

            if (user.getRole().equals("admin")) {

                ctx.render("admin.html");
            } else {
                ctx.render("order.html");
            }


        } catch (DatabaseException e) {
            //Hvis nej send tilbage til loginside med fejlbesked
            ctx.attribute("message", e.getMessage());
            ctx.render("index.html");
        }


    }

    public void updateBalance(){

    }

//    public static void getAllUsers(Context ctx, ConnectionPool connectionPool) {
//        //Hent formparametre
//        int user_id = Integer.parseInt(ctx.formParam("user_Id"));
//        String username = ctx.formParam("username");
//        String password = ctx.formParam("password");
//        String role = ctx.formParam("role");
//        int balance = Integer.parseInt(ctx.formParam("balance"));
//
//
//        try {
//            User user = (User) UserMapper.getAllUsers(user_id,username, password, role, balance, connectionPool);
//            ctx.sessionAttribute("currentUser", user);
//
//            ctx.render("order.html");
//
//
//        } catch (DatabaseException e) {
//            ctx.attribute("message", e.getMessage());
//            ctx.render("index.html");
//        }
//    }

}

