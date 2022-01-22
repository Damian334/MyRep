package com.revature.app.views;

import com.revature.model.User;
import io.javalin.http.Context;

public class UserView {

    private static String basicUrl;

    public UserView(String basicUrl) {
        this.basicUrl = basicUrl;
    }

    public static User cookieUser(Context c) {
        User user = null;
        try {
            String name = c.cookieStore("name");
            String pass = c.cookieStore("pass");
            user = User.login(name, pass);
        } catch (Exception e) {
        }
        return user;
    }

    public static String notLogged() {
        String users = basicUrl + "/users";
        return format("<div>Please first login <a href='" + users + "'>Here</a></div>\n");
    }

    private static String format(String str) {
        return "<html>\n<head>\n</head>\n<body>\n"
                + str
                + "</body></html>\n";
    }

    public String userPage(User user) {
        String userLoginForm = basicUrl + "/users/loginForm";
        String userLogout = basicUrl + "/users/logout";
        String userSignup = basicUrl + "/users/signupForm";
        String accounts = basicUrl + "/accounts";

        String res = "<h1>User Page</h1>\n";
        if (user == null) {
            res += "<div><a href='" + userLoginForm + "'>Login</a></div>\n"
                    + "<div><a href='" + userSignup + "'>SignUp</a></div>\n";
        } else {
            res += "<div>Hello " + user.getName() + "!</div>\n"
                    + "<div><a href='" + userLogout + "'>Logout</a></div>\n"
                    + "<div><a href='" + accounts + "'>Accounts</a></div>\n";
        }
        return format(res);
    }

    public String userSignUpForm() {
        String userSignUp = basicUrl + "/users/signup";

        String res = "<h1>SignUp Page</h1>\n"
                + "<form action=\"" + userSignUp + "\" method=\"post\">\n"
                + "  <label for=\"name\">Login:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>\n"
                + "  <input type=\"text\" id=\"name\" name=\"name\"><br><br>\n"
                + "  <label for=\"pass\">Password: </label>\n"
                + "  <input type=\"password\" id=\"pass\" name=\"pass\"><br><br>\n"
                + "  <input type=\"submit\" value=\"Submit\">\n"
                + "</form>";
        return format(res);
    }

    public String userLoginForm() {
        String userLogin = basicUrl + "/users/login";

        String res = "<h1>Login Page</h1>\n"
                + "<form action=\"" + userLogin + "\" method=\"post\">\n"
                + "  <label for=\"name\">Login:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>\n"
                + "  <input type=\"text\" id=\"name\" name=\"name\"><br><br>\n"
                + "  <label for=\"pass\">Password: </label>\n"
                + "  <input type=\"password\" id=\"pass\" name=\"pass\"><br><br>\n"
                + "  <input type=\"submit\" value=\"Submit\">\n"
                + "</form>\n";
        return format(res);
    }
}
