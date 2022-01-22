package com.revature.app;

import com.revature.controllers.AccountController;
import com.revature.controllers.AdminController;
import com.revature.controllers.UserController;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class DriverJavelin {

    public static String basicUrl = "http://localhost:7070";

    public static void main(String[] args) {
        DriverJavelin driver = new DriverJavelin();
        driver.run();
    }

    public void run() {
        Javalin app = Javalin.create().start(7070);
        UserController users = new UserController(app, basicUrl);
        AccountController accounts = new AccountController(app, basicUrl);
        AdminController admin = new AdminController(app, basicUrl);
        

        app.get("/", mainPage);
    }

    public Handler mainPage = crt -> {
        String userUrl = basicUrl + "/users";

        String res = "<html><head></head><body>"
                + "<h1>Main Page</h1>"
                + "<a href='" + userUrl + "'>users</a>"
                + "</body></html>";
        crt.html(res);
    };

}
