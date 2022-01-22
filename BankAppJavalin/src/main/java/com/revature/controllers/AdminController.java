package com.revature.controllers;

import com.revature.app.views.AdminView;
import com.revature.app.views.UserView;
import com.revature.model.worker.Admin;
import com.revature.model.Account;
import com.revature.model.User;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class AdminController {

    private Javalin app;
    private String basicUrl;
    private AdminView view;

    public AdminController(Javalin app, String basicUrl) {
        this.app = app;
        this.basicUrl = basicUrl;
        this.view = new AdminView(basicUrl);

        app.get("/admin", adminPage);
        app.get("/admin/accInfo", accountsInfo);
        app.get("/admin/accBalances", accountsBalances);
        app.get("/admin/usersInfo", usersInfo);//approve
        app.get("/admin/approve", approveInfo);
        app.get("/admin/approve/{number}", approve);
        app.get("/admin/deposit/{name}/{number}", deposit);
        app.get("/admin/withdraw/{name}/{number}", withdraw);
        app.get("/admin/transfer/{name}/{name2}/{number}", transfer);
        app.get("/admin/remove/{name}", remove);
    }

    public Handler adminPage = ctx -> {
        String res = view.adminPage();
        ctx.html(res);
    };

    public Handler accountsInfo = ctx -> {
        Admin a = new Admin();
        String msg = a.showAccountsInformation();
        String res = view.info(msg);
        ctx.html(res);
    };

    public Handler accountsBalances = ctx -> {
        Admin a = new Admin();
        String msg = a.showAccountsBalances();
        String res = view.info(msg);
        ctx.html(res);
    };

    public Handler usersInfo = ctx -> {
        Admin a = new Admin();
        String msg = a.showUsersInformation();
        String res = view.info(msg);
        ctx.html(res);
    };

    public Handler approveInfo = ctx -> {
        Admin a = new Admin();
        String msg = a.approveShowAccounts();
        String res = view.info(msg);
        ctx.html(res);
    };

    public Handler approve = ctx -> {
        Admin a = new Admin();
        String[] msg = ctx.pathParam("number").split(" ");
        Integer nr = convertString(msg[0]);
        if (nr == null) {
            ctx.status(400);
        }
        if (a.approveAccounts(msg[1], nr)) {
            ctx.html(view.format("<div>account got approved</div>"));
        } else {
            ctx.status(400);
        }
    };

    private Integer convertString(String nr) {
        Integer fund = null;
        try {
            fund = Integer.valueOf(nr);
        } catch (Exception e) {
        }
        return fund;
    }

    private Double convertString2(String funds) {
        Double fund = null;
        try {
            fund = Double.valueOf(funds);
        } catch (Exception e) {
        }
        return fund;
    }

    public Handler deposit = ctx -> {
        User admin = new User("admin", "");
        String accName = ctx.pathParam("name");
        Double funds = convertString2(ctx.pathParam("number"));
        Account ac = admin.getUserConfirmedAccount(accName);
        Object[] ob = ac.deposit(admin, funds);
        ctx.result(ob[1].toString());
    };

    public Handler withdraw = ctx -> {
        User admin = new User("admin", "");
        String accName = ctx.pathParam("name");
        Double funds = convertString2(ctx.pathParam("number"));
        Account ac = admin.getUserConfirmedAccount(accName);
        Object[] ob = ac.withdraw(admin, funds);
        ctx.result(ob[1].toString());
    };

    public Handler transfer = ctx -> {
        User admin = new User("admin", "");
        String accName = ctx.pathParam("name");
        String accName2 = ctx.pathParam("name2");
        Double funds = convertString2(ctx.pathParam("number"));
        Account ac = admin.getUserConfirmedAccount(accName);
        Object[] ob = ac.transfer(admin, funds, accName2);
        ctx.result(ob[1].toString());
    };

    public Handler remove = ctx -> {
        Admin a = new Admin();
        String accName = ctx.pathParam("name");
        if (a.removeAccountByName(accName)) {
            ctx.html(view.format("<div>account got removed</div>"));
        } else {
            ctx.html(view.format("<div>the account \"" + accName + "\" dosnt exist</div>"));
        }
    };
}
