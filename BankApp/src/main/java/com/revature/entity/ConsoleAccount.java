package com.revature.entity;

import java.util.Scanner;

public class ConsoleAccount extends Account {

    public ConsoleAccount() {
    }

    public ConsoleAccount(Account ac) {
        this.id = ac.id;
        this.funds = ac.funds;
        this.accountName = ac.accountName;
        this.applicants = ac.applicants;
    }

    public boolean createAccount(User user) {
        Scanner scn = new Scanner(System.in);
        System.out.println("write account name:");
        String name = scn.nextLine();
        Object[] msg = super.createAccount(user, name);
        System.out.println(msg[1]);
        if (msg[2] != null) {
            System.out.println("createAccount?");
            ConsoleAccount ac = new ConsoleAccount((Account) msg[2]);
            this.accountsPool.add(ac);
        }
        return (boolean) msg[0];
    }

    public boolean addApplicant(User user) {
        Scanner scn = new Scanner(System.in);
        System.out.println("write account name:");
        String name = scn.nextLine();
        Object[] msg = super.addApplicant(user, name);
        System.out.println(msg[1]);
        return (boolean) msg[0];
    }

    public boolean deposit(User user) {
        Scanner scn = new Scanner(System.in);
        Double money = null;
        try {
            System.out.println("enter a number of money:");
            money = Double.valueOf(scn.nextLine());
        } catch (Exception e) {
            System.out.println("error you typed wrong value");
        }
        Object[] msg = super.deposit(user, money);
        System.out.println(msg[1]);
        return (boolean) msg[0];
    }

    public boolean withdraw(User user) {
        Scanner scn = new Scanner(System.in);
        Double money = null;
        try {
            System.out.println("enter a number of money:");
            money = Double.valueOf(scn.nextLine());
        } catch (Exception e) {
            System.out.println("error you typed wrong value");
        }
        Object[] msg = super.withdraw(user, money);
        System.out.println(msg[1]);
        return (boolean) msg[0];
    }

    public boolean transfer(User user) {
        Scanner scn = new Scanner(System.in);
        Double money = null;
        String accName = "";
        try {
            System.out.println("enter a number of money:");
            money = Double.valueOf(scn.nextLine());
            System.out.println("type a name of account where you want transfer funds");
            accName = scn.nextLine();
        } catch (Exception e) {
            System.out.println("error you typed wrong value");
        }
        Object[] msg = super.transfer(user, money, accName);
        System.out.println(msg[1]);
        return (boolean) msg[0];
    }

}
