package com.revature.controllers;

import com.revature.app.views.UserView;
import com.revature.model.User;
import io.javalin.Javalin;
import io.javalin.http.Handler;

//https://javalin.io/archive/docs/v1.7.0.html#cookie-store
public class UserController {

    private Javalin app;
    private String basicUrl;
    private UserView view;

    public UserController(Javalin app, String basicUrl) {
        this.app = app;
        this.basicUrl = basicUrl;
        this.view = new UserView(basicUrl);

        app.get("/users", userPage);
        app.get("/users/logout", userLogout);
        app.get("/users/loginForm", userLoginForm);
        app.post("/users/login", userLogin);//read data from loggin user
        app.get("/users/signupForm", userSignUpForm);
        app.post("/users/signup", userSignUp);
    }

    public Handler userPage = ctx -> {
        User user = view.cookieUser(ctx);
        String res = view.userPage(user);
        ctx.html(res);
    };

    public Handler userLogout = ctx -> {
        //clear users cookies
        ctx.clearCookieStore();
        ctx.redirect(basicUrl + "/users");
    };

    public Handler userSignUpForm = ctx -> {
        String res = view.userSignUpForm();
        ctx.html(res);
    };

    public Handler userSignUp = ctx -> {
        //user registration
        System.out.println("userSignUp: ?");
        String name = ctx.formParam("name");
        String pass = ctx.formParam("pass");
        User u = new User();
        Object[] ob = u.registration(name, pass);
        boolean b = (boolean) ob[0];
        if (b) {
            User user = User.login(name, pass);
            ctx.cookieStore("name", name);
            ctx.cookieStore("pass", pass);
            ctx.redirect(basicUrl + "/users");
        }
        ctx.result(ob[1].toString());
        System.out.println("userSignUp: " + ob[1].toString());
    };

    public Handler userLoginForm = ctx -> {
        String res = view.userLoginForm();
        ctx.html(res);
    };

    public Handler userLogin = ctx -> {
        //i need to cheack if login/password is correct
        String name = ctx.formParam("name");
        String pass = ctx.formParam("pass");

        User user = User.login(name, pass);
        if (user != null) {
            ctx.cookieStore("name", name);
            ctx.cookieStore("pass", pass);
            //and can show or load page what logged user can do with account
            ctx.redirect(basicUrl + "/users");
        } else {
            //eg print some error
            ctx.status(400);
        }
    };
}
