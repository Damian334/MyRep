package com.revature.entity;

import com.revature.app.Bank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Account implements Serializable {

    private static final Logger logger = LogManager.getLogger(Account.class);
    public final Set<Account> accountsPool = Bank.accountsPool;
    private int id;
    private double funds = 0;
    private String accountName = null;//e.g 4digitals~
    private Set<Applicant> applicants = new HashSet<Applicant>();

    private List<String> fundsOperations = new ArrayList<String>();

    public Account() {

    }

    //only for test
    public Account(int id, String accountName) {
        this.id = id;
        this.accountName = accountName;
        this.funds = funds;
    }    
    
    public boolean createAccount(User user) {
        Scanner scn = new Scanner(System.in);

        System.out.println("write account name:");
        String name = scn.nextLine();
        if (name.contains(";")) {
            System.out.println("name can't contain sing \";\"");
            return false;
        }

        for (Account a : accountsPool) {
            if (a.accountName.equals(name)) {
                System.out.println("account \"" + name + "\" already exist");
                return false;
            }
        }
        accountName = name;
        applicants.add(new Applicant(user));
        int i = 1;
        for (Account a : accountsPool) {
            if (a.id > i) {
                i = a.id + 1;
            }
        }
        this.id = i;
        accountsPool.add(this);
        //user.accounts.add(this);
        System.out.println("account \"" + name + "\" is created");
        logger.info("account \"" + name + "\" is created");
        return true;
    }

    //use when I want to add customer to existing account
    public boolean addApplicant(User user) {
        Scanner scn = new Scanner(System.in);

        System.out.println("write account name:");
        String name = scn.nextLine();
        for (Account a : accountsPool) {
            if (a.accountName.equals(name)) {
                Applicant ap = new Applicant(user);
                if (a.getApplicants().contains(ap)) {
                    System.out.println("account " + name + " already have joint customer \"" + user.name + "\"");
                    return false;
                }
                a.applicants.add(ap);
                System.out.println(user.name + " joint the account \"" + name + "\"");
                logger.info(user.name + " joint the account \"" + name + "\"");
                return true;
            }
        }
        System.out.println("account \"" + name + "\" dosn't exist");
        return false;
    }

    @Override
    public String toString() {
        return "id: " + id + " , name: " + accountName + " , funds: " + funds;
    }

    public boolean deposit(User user) {
        Scanner scn = new Scanner(System.in);
        try {
            System.out.println("enter a number of money:");
            double money = Double.valueOf(scn.nextLine());
            if (money < 0) {
                System.out.println("you can't deposit negative amounts");
                return false;
            } else {
                this.funds += money;
                this.fundsOperations.add("user " + user.name + " deposited: " + money);
                System.out.println("user " + user.name + " deposited: " + money);
                logger.info("user " + user.name + " deposited: " + money);
                return true;
            }
        } catch (Exception e) {
            logger.error("deposit error for " + user.name);
            System.out.println("error you typed wrong value");
        }
        return false;
    }

    public boolean withdraw(User user) {
        Scanner scn = new Scanner(System.in);
        try {
            System.out.println("enter a number of money:");
            double money = Double.valueOf(scn.nextLine());
            if (money < 0) {
                System.out.println("you can't withdraw negative amounts");
                return false;
            } else if (this.funds < money) {
                System.out.println("there is no such amount of funds in this account");
                return false;
            } else {
                this.funds -= money;
                this.fundsOperations.add("user " + user.name + " withdraw: " + money);
                System.out.println("user " + user.name + " withdraw: " + money);
                logger.info("user " + user.name + " withdraw: " + money);
                return true;
            }
        } catch (Exception e) {
            logger.error("withdraw error for " + user.name);
            System.out.println("error you typed wrong value");
        }
        return false;
    }

    public boolean transfer(User user) {
        Scanner scn = new Scanner(System.in);
        try {
            System.out.println("enter a number of money:");
            double money = Double.valueOf(scn.nextLine());
            if (money < 0) {
                System.out.println("you can't withdraw negative amounts");
                return false;
            } else if (this.funds < money) {
                System.out.println("there is no such amount of funds in this account");
                return false;
            } else {
                System.out.println("type a name of account where you want transfer funds");
                String accName = scn.nextLine();
                for (Account a : accountsPool) {
                    if (a.getAccountName().equals(accName)) {
                        a.funds += money;
                        this.funds -= money;
                        this.fundsOperations.add("user " + user.name + " transfered: " + money + " to " + a.getAccountName());
                        a.fundsOperations.add("user " + user.name + " transfered: " + money + " to " + a.getAccountName());
                        System.out.println("user " + user.name + " transfered: " + money + " to " + a.getAccountName());
                        logger.info("user " + user.name + " transfered: " + money + " to " + a.getAccountName());
                        return true;
                    }
                }
                System.out.println("the account \"" + accName + "\" dosn't exist");
                return false;
            }
        } catch (Exception e) {
            logger.error("transfer error for " + user.name);
            System.out.println("error you typed wrong value");
        }
        return false;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public Set<Applicant> getApplicants() {
        return this.applicants;
    }

    public double getFunds() {
        return this.funds;
    }

    public List<String> getFundsOperations() {
        return fundsOperations;
    }

    @Override
    public int hashCode() {
        return this.accountName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (this.accountName.equals(other.accountName)) {
            return true;
        }
        return false;
    }
    
}
