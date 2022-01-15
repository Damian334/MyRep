package com.revature.entity.console;

import com.revature.entity.User;
import java.util.Scanner;

public class ConsoleUser extends User {

    public ConsoleUser() {
    }

    public ConsoleUser(String name, String password) {
        super(name, password);
    }

    public ConsoleUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
    }

    public boolean registration() {
        Scanner scn = new Scanner(System.in);
        System.out.println("enter name:");
        String name = scn.nextLine();
        System.out.println("enter password:");
        String password = scn.nextLine();
        Object[] msg = super.registration(name, password);
        System.out.println(msg[1]);
        return (boolean) msg[0];
    }

    public static ConsoleUser login() {
        Scanner scn = new Scanner(System.in);
        System.out.println("type user name");
        String name = scn.nextLine();
        System.out.println("type user password");
        String password = scn.nextLine();
        User user = User.login(name, password);
        if (user == null) {
            return null;
        } else {
            return new ConsoleUser(user);
        }
    }

    public boolean addAccountToUser() {
        Scanner scn = new Scanner(System.in);
        System.out.println("do you want to:");
        System.out.println("1. open new an account");
        System.out.println("2. joint to already existing account");
        Integer nr = null;
        String accountName = "";
        try {
            nr = Integer.valueOf(scn.nextLine());
        } catch (Exception e) {
            System.out.println("incorrect input");
            super.logger.error("addAccountToUser for user " + this.name + " error: " + e);
            return false;
        }
        System.out.println("write account name:");
        accountName = scn.nextLine();
        Object[] res = super.addAccountToUser(nr, accountName);
        if (res == null) {
            return false;
        }
        System.out.println(res[1]);
        return (boolean) res[0];
    }
}
