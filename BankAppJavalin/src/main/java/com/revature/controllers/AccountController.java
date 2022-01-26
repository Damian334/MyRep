package com.revature.controllers;

import com.revature.app.views.AccountView;
import com.revature.app.views.UserView;
import com.revature.model.Account;
import com.revature.model.User;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class AccountController {

    private Javalin app;
    private String basicUrl;
    private AccountView view;

    public AccountController(Javalin app, String basicUrl) {
        this.app = app;
        this.basicUrl = basicUrl;
        this.view = new AccountView(basicUrl);

        app.get("/accounts", accountPage);
        app.get("/accounts/add", addAccountForm);
        app.post("/accounts/addAccount", addAccount);
        app.get("/accounts/addJoint", addJointForm);
        app.post("/accounts/addJoint2", addJoint);
        app.get("/accounts/showAccount", showAccounts);

        app.get("/accounts/deposit", depositForm);
        app.post("/accounts/deposit2", deposit);

        app.get("/accounts/withdraw", withdrawForm);
        app.post("/accounts/withdraw2", withdraw);

        app.get("/accounts/transfer", transferForm);
        app.post("/accounts/transfer2", transfer);

    }

    public Handler accountPage = ctx -> {
        User user = UserView.cookieUser(ctx);
        if(user==null){ctx.html(UserView.notLogged());return;}
        String res = view.accountPage(user);
        ctx.html(res);
    };

    public Handler addAccountForm = ctx -> {
        User user = UserView.cookieUser(ctx);
        if(user==null){ctx.html(UserView.notLogged());return;}
        String res = view.addAccountForm(user);
        ctx.html(res);
    };

    public Handler addAccount = ctx -> {
        User user = UserView.cookieUser(ctx);
        if(user==null){ctx.html(UserView.notLogged());return;}
        String name = ctx.formParam("name");
        Account ac = new Account();
        Object[] ob = ac.createAccount(user, name);
        ctx.result(ob[1].toString());
    };

    ///////////////
    public Handler addJointForm = ctx -> {
        User user = UserView.cookieUser(ctx);
        if(user==null){ctx.html(UserView.notLogged());return;}
        String res = view.addAccountForm(user);
        ctx.html(res);
    };

    public Handler addJoint = ctx -> {
        User user = UserView.cookieUser(ctx);
        if(user==null){ctx.html(UserView.notLogged());return;}
        String name = ctx.formParam("name");
        Account ac = new Account();
        Object[] ob = ac.addApplicant(user, name);
        ctx.result(ob[1].toString());
    };

    ///////////////
    public Handler showAccounts = ctx -> {
        User user = UserView.cookieUser(ctx);
        if(user==null){ctx.html(UserView.notLogged());return;}
        String info = user.getUserConfirmedAccountInfo();
        String res = view.showAccounts(info);
        ctx.html(res);
    };

    private Double convertString(String funds) {
        Double fund = null;
        try {
            fund = Double.valueOf(funds);
        } catch (Exception e) {
        }
        return fund;
    }

    ///////////////
    public Handler depositForm = ctx -> {
        User user = UserView.cookieUser(ctx);
        if(user==null){ctx.html(UserView.notLogged());return;}
        String res = view.depositForm(user);
        ctx.html(res);
    };

    public Handler deposit = ctx -> {
        User user = UserView.cookieUser(ctx);
        if(user==null){ctx.html(UserView.notLogged());return;}
        String accountName = ctx.formParam("name");
        Double funds = convertString(ctx.formParam("funds"));
        Account ac = user.getUserConfirmedAccount(accountName);
        Object[] ob = ac.deposit(user, funds);
        ctx.result(ob[1].toString());
    };
    ///////////////////////////////////////

    public Handler withdrawForm = ctx -> {
        User user = UserView.cookieUser(ctx);
        if(user==null){ctx.html(UserView.notLogged());return;}
        String res = view.withdrawForm(user);
        ctx.html(res);
    };

    public Handler withdraw = ctx -> {
        User user = UserView.cookieUser(ctx);
        if(user==null){ctx.html(UserView.notLogged());return;}
        String accountName = ctx.formParam("name");
        Double funds = convertString(ctx.formParam("funds"));
        Account ac = user.getUserConfirmedAccount(accountName);
        Object[] ob = ac.withdraw(user, funds);
        ctx.result(ob[1].toString());
    };

    ///////////////////////////////////////
    public Handler transferForm = ctx -> {
        User user = UserView.cookieUser(ctx);
        if(user==null){ctx.html(UserView.notLogged());return;}
        String res = view.transferForm(user);
        ctx.html(res);
    };

    public Handler transfer = ctx -> {
        User user = UserView.cookieUser(ctx);
        if(user==null){ctx.html(UserView.notLogged());return;}
        String accountName = ctx.formParam("name");
        String accountName2 = ctx.formParam("name2");
        Double funds = convertString(ctx.formParam("funds"));
        Account ac = user.getUserConfirmedAccount(accountName);
        Object[] ob = ac.transfer(user, funds, accountName2);
        ctx.result(ob[1].toString());
    };

}
