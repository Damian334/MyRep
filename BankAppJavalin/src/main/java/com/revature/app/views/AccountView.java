package com.revature.app.views;

import com.revature.model.User;

public class AccountView {

    private static String basicUrl;

    public AccountView(String basicUrl) {
        this.basicUrl = basicUrl;
    }

    private static String format(String str) {
        return "<html>\n<head>\n</head>\n<body>\n"
                + str
                + "</body></html>\n";
    }

    public String accountPage(User user) {
        String users = basicUrl + "/users";
        String addAccount = basicUrl + "/accounts/add";
        String jointAccount = basicUrl + "/accounts/addJoint";
        String availableAccounts = basicUrl + "/accounts/showAccount";
        String deposit = basicUrl + "/accounts/deposit";
        String withdraw = basicUrl + "/accounts/withdraw";
        String transfer = basicUrl + "/accounts/transfer";

        String res = "<h1>Account Page</h1>\n"
                    + "<div><a href='" + users + "'>users page</a></div>\n"
                    + "<div><a href='" + addAccount + "'>Create account</a></div>\n"
                    + "<div><a href='" + jointAccount + "'>Joint account</a></div>\n"
                    + "<div><a href='" + availableAccounts + "'>Show available accounts</a></div>\n"
                    + "<div><a href='" + deposit + "'>Deposit</a></div>\n"
                    + "<div><a href='" + withdraw + "'>Withdraw</a></div>\n"
                    + "<div><a href='" + transfer + "'>Transfer</a></div>\n";
        
        return format(res);
    }

    public String addAccountForm(User user) {
        String add = basicUrl + "/accounts/addAccount";

        String res = "<h1>Open new account</h1>\n"
                + "<form action=\"" + add + "\" method=\"post\">\n"
                + "  <label for=\"name\">Account Name: </label>\n"
                + "  <input type=\"text\" id=\"name\" name=\"name\"><br><br>\n"
                + "  <input type=\"submit\" value=\"Submit\">\n"
                + "</form>";
        return format(res);
    }

    public String addJoint(User user) {
        String add = basicUrl + "/accounts/addJoint2";

        String res = "<h1>Joint account</h1>\n"
                + "<form action=\"" + add + "\" method=\"post\">\n"
                + "  <label for=\"name\">Account Name: </label>\n"
                + "  <input type=\"text\" id=\"name\" name=\"name\"><br><br>\n"
                + "  <input type=\"submit\" value=\"Submit\">\n"
                + "</form>";
        return format(res);
    }

    public String showAccounts(String info) {
        String res = "";
        for (String s : info.split("\n")) {
            res += "<div>" + s + "</div>\n";
        }
        return format(res);
    }

    public String depositForm(User user) {
        String add = basicUrl + "/accounts/deposit2";

        String res = "<h1>Deposit funds</h1>\n"
                + "<form action=\"" + add + "\" method=\"post\">\n"
                + "  <label for=\"name\">Account Name: </label>\n"
                + "  <input type=\"text\" id=\"name\" name=\"name\"><br><br>\n"
                + "  <label for=\"funds\">Funds: </label>\n"
                + "  <input type=\"text\" id=\"funds\" name=\"funds\"><br><br>\n"
                + "  <input type=\"submit\" value=\"Submit\">\n"
                + "</form>";
        return format(res);
    }

    public String withdrawForm(User user) {
        String add = basicUrl + "/accounts/withdraw2";

        String res = "<h1>Withdraw funds</h1>\n"
                + "<form action=\"" + add + "\" method=\"post\">\n"
                + "  <label for=\"name\">Account Name: </label>\n"
                + "  <input type=\"text\" id=\"name\" name=\"name\"><br><br>\n"
                + "  <label for=\"funds\">Funds: </label>\n"
                + "  <input type=\"text\" id=\"funds\" name=\"funds\"><br><br>\n"
                + "  <input type=\"submit\" value=\"Submit\">\n"
                + "</form>";
        return format(res);
    }

    public String transferForm(User user) {
        String add = basicUrl + "/accounts/transfer2";

        String res = "<h1>Withdraw funds</h1>\n"
                + "<form action=\"" + add + "\" method=\"post\">\n"
                + "  <label for=\"name\">Your  Account Name: </label>\n"
                + "  <input type=\"text\" id=\"name\" name=\"name\"><br><br>\n"
                + "  <label for=\"name2\">Target Account Name: </label>\n"
                + "  <input type=\"text\" id=\"name2\" name=\"name2\"><br><br>\n"
                + "  <label for=\"funds\">Funds: </label>\n"
                + "  <input type=\"text\" id=\"funds\" name=\"funds\"><br><br>\n"
                + "  <input type=\"submit\" value=\"Submit\">\n"
                + "</form>";
        return format(res);
    }
}
