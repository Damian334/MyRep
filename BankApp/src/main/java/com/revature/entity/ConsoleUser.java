package com.revature.entity;

import java.util.Scanner;
import java.util.Set;

public class ConsoleUser extends User {

    public ConsoleUser() {
    }

    public ConsoleUser(String name, String password) {
        super(name, password);
    }

    public ConsoleUser(User user) {
        this.id = user.id;
        this.name = user.name;
        this.password = user.password;
        this.accountsPool = user.accountsPool;
    }

    public boolean registration(Set<User> usersPool) {
        Scanner scn = new Scanner(System.in);
        System.out.println("enter name:");
        String name = scn.nextLine();
        System.out.println("enter password:");
        String password = scn.nextLine();
        Object[] msg = super.registration(usersPool, name, password);
        if (msg[2] != null) {
            ConsoleUser user = new ConsoleUser((ConsoleUser) msg[2]);
            usersPool.add(user);
        }
        System.out.println(msg[1]);
        return (boolean) msg[0];
    }

    public static ConsoleUser login(Set<User> usersPool) {
        Scanner scn = new Scanner(System.in);
        System.out.println("type user name");
        String name = scn.nextLine();
        System.out.println("type user password");
        String password = scn.nextLine();
        User user = User.login(usersPool, name, password);
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
        String err = "";
        String name = "";
        try {
            nr = Integer.valueOf(scn.nextLine());
            System.out.println("write account name:");
            name = scn.nextLine();
        } catch (Exception e) {
            System.out.println("error: " + err);
            return false;
        }
        Object[] res = super.addAccountToUser(nr, err, name);
        System.out.println(res[1]);
        if (res.length == 3) {
            ConsoleAccount ac = new ConsoleAccount((Account) res[2]);
            accountsPool.add(ac);
        }
        return (boolean) res[0];
    }
}