package com.revature.entity;

import com.revature.app.Bank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Customer
public class User implements Serializable {

    private static final Logger logger = LogManager.getLogger(User.class);
    public int id;
    public String name = null;
    public String password = null;
    private Set<Account> accountsPool = Bank.accountsPool;
    
    public User() {

    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    
    public boolean registration(Set<User> usersPool) {
        Scanner scn = new Scanner(System.in);

        System.out.println("enter name:");
        name = scn.nextLine();
        System.out.println("enter password:");
        password = scn.nextLine();

        if(name.contains(";") || password.contains(";")){
            System.out.println("name or password can't contain sing \";\"");
            return false;
        }
        
        boolean check = false;
        for (User u : usersPool) {
            if (u.name.equals(name)) {
                check = true;
                break;
            }
        }
        if (check) {
            System.out.println("user already exist");
        } else {
            int i = 1;
            for (User us : usersPool) {
                if (us.id >= i) {
                    i = us.id + 1;
                }
            }
            this.id = i;
            usersPool.add(this);
            System.out.println("user \""+this.name+"\" get created");
            logger.info("user \""+this.name+"\" get created");
        }
        return !check;//return true when user get created
    }

    public static User login(Set<User> usersPool) {
        Scanner scn = new Scanner(System.in);
        System.out.println("type user name");
        String name = scn.nextLine();
        System.out.println("type user password");
        String password = scn.nextLine();

        for (User user : usersPool) {
            if (user.name.equals(name) && user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }

    //not tested because it could be double later because of switch
    public boolean addAccountToUser() {
        Scanner scn = new Scanner(System.in);

        System.out.println("do you want to:");
        System.out.println("1. open new an account");
        System.out.println("2. joint to already existing account");
        try {
            int nr = Integer.valueOf(scn.nextLine());
            Account ac = new Account();
            switch (nr) {
                case 1:
                    return ac.createAccount(this);
                case 2:
                    return ac.addApplicant(this);
                default:
                    return false;
            }
        } catch (Exception e) {
            //logger
            logger.error("addAccountToUser for user "+this.name+" error: "+e);
            System.out.println("error: " + e);
            return false;
        }
    }

    @Override
    public String toString() {
        return "name: " + name + " , password: " + password;
    }

    ///////////////////////////////////////////////////////////////////////////////////
    //this for admin
    public Set<Account> getUserAccounts() {
        Set<Account> list = new HashSet<>();
        for (Account ac : accountsPool) {
            for (Applicant ap : ac.getApplicants()) {
                if (ap.getUser().name.equals(this.name)) {
                    list.add(ac);
                }
            }
        }
        return list;
    }

    public Set<Account> getUserConfirmedAccounts() {
        Set<Account> list = new HashSet<>();
        for (Account ac : accountsPool) {
            for (Applicant ap : ac.getApplicants()) {
                if (ap.getUser().name.equals(this.name) && ap.getStatus()) {
                    list.add(ac);
                }
            }
        }
        return list;
    }

    public String getUserConfirmedAccountInfo() {
        Set<Account> list = getUserConfirmedAccounts();
        String str = "user confirmed accounts:\n";
        for (Account a : list) {
            str += "name: " + a.getAccountName() + " , funds: " + a.getFunds() + "\n";
        }
        return str;
    }

    public Account getUserConfirmedAccount(String accountName) {
        for (Account ac : accountsPool) {
            if (ac.getAccountName().equals(accountName)) {
                for (Applicant ap : ac.getApplicants()) {
                    if (ap.getUser().name.equals(this.name) && ap.getStatus()) {
                        return ac;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (this.name.equals(other.name)) {
            return true;
        }
        return false;
    }    
}