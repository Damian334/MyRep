package com.revature.app;

import com.revature.entity.Account;
import com.revature.entity.User;
import com.revature.entity.worker.Admin;
import com.revature.entity.worker.Employe;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bank {

    private static final Logger logger = LogManager.getLogger(Bank.class);
    public static Set<User> usersPool = new HashSet<>();
    public static Set<Account> accountsPool = new HashSet<>();

    public void run() {
        Scanner scn = new Scanner(System.in);
        this.read();//load at begin
        while (true) {
            System.out.println("select options: ");
            System.out.println("1. Customer");
            System.out.println("2. Employe");
            System.out.println("3. Admin");
            System.out.println("to exit type anything");
            String nr = scn.nextLine();
            switch (nr) {
                case "1":
                    userSelected(scn);
                    break;
                case "2":
                    employeSelected(scn);
                    break;
                case "3":
                    adminSelected(scn);
                    break;
                default:
                    System.out.println("usersPool " + usersPool.size() + " , accountsPool " + accountsPool.size());
                    this.save();//save at end
                    return;
            }
        }
    }

    private void userSelected(Scanner scn) {

        while (true) {
            System.out.println("select options: ");
            System.out.println("1. login");
            System.out.println("2. registration");
            System.out.println("to exit type anything");
            String nr = scn.nextLine();
            switch (nr) {
                case "1":
                    User u = User.login(usersPool);
                    if (u != null) {
                        userLoged(scn, u);
                    } else {
                        System.out.println("your login or password is incorrect");
                        scn.nextLine();
                        return;
                    }
                    return;
                case "2":
                    User u2 = new User();
                    u2.registration(usersPool);
                    return;
                default:
                    return;
            }
        }
    }

    private void userLoged(Scanner scn, User user) {
        while (true) {
            System.out.println("select options: ");
            System.out.println("1. add account");
            System.out.println("2. show user confirmed accounts");
            System.out.println("3. select account by typing name");
            System.out.println("to exit type anything");
            String nr = scn.nextLine();
            switch (nr) {
                case "1":
                    user.addAccountToUser();
                    break;
                case "2":
                    System.out.println(user.getUserConfirmedAccountInfo());//not working till i will add admin
                    break;
                case "3":
                    String accountName = scn.nextLine();
                    Account ac = user.getUserConfirmedAccount(accountName);
                    if (ac != null) {
                        userLogedAccountOperations(scn, user, ac);
                    } else {
                        System.out.println("the account \"" + accountName + "\" dosnt exist");
                    }
                    break;
                default:
                    return;
            }
        }
    }

    private void userLogedAccountOperations(Scanner scn, User user, Account ac) {
        while (true) {
            System.out.println("select options: ");
            System.out.println("1. deposit");
            System.out.println("2. withdraw");
            System.out.println("3. transfer funds on other accounts");
            System.out.println("to exit type anything");
            String nr = scn.nextLine();
            switch (nr) {
                case "1":
                    ac.deposit(user);
                    break;
                case "2":
                    ac.withdraw(user);
                    break;
                case "3":
                    ac.transfer(user);
                    break;
                default:
                    return;
            }
        }
    }

    private void employeSelected(Scanner scn) {
        while (true) {
            System.out.println("select options: ");
            System.out.println("1. show all accounts information");
            System.out.println("2. show all accounts balances");
            System.out.println("3. show all users information");
            System.out.println("4. show all no accepted applications for accounts");
            System.out.println("to exit type anything");
            String nr = scn.nextLine();
            Employe emp = new Employe(this.usersPool, this.accountsPool);
            switch (nr) {
                case "1":
                    emp.showAccountsInformation();
                    break;
                case "2":
                    emp.showAccountsBalances();
                    break;
                case "3":
                    emp.showUsersInformation();
                    break;
                case "4":
                    emp.approveAccounts();
                    break;
                default:
                    return;
            }
        }
    }

    private void adminSelected(Scanner scn) {
        while (true) {
            System.out.println("select options: ");
            System.out.println("1. show all accounts information");
            System.out.println("2. show all accounts balances");
            System.out.println("3. show all users information");
            System.out.println("4. show all no accepted applications for accounts");
            System.out.println("5. select account by typing name to edit");
            System.out.println("6. select account by typing name to remove");

            System.out.println("to exit type anything");
            String nr = scn.nextLine();
            Admin adm = new Admin(this.usersPool, this.accountsPool);
            switch (nr) {
                case "1":
                    adm.showAccountsInformation();
                    break;
                case "2":
                    adm.showAccountsBalances();
                    break;
                case "3":
                    adm.showUsersInformation();
                    break;
                case "4":
                    adm.approveAccounts();
                    break;
                case "5":
                    User admin = new User("admin", "");
                    String accountName = scn.nextLine();
                    Account ac = adm.getAccountByName(accountName);
                    if (ac != null) {
                        userLogedAccountOperations(scn, admin, ac);
                    } else {
                        System.out.println("the account \"" + accountName + "\" dosnt exist");
                    }
                    break;
                case "6":
                    String accountName2 = scn.nextLine();
                    Account ac2 = adm.getAccountByName(accountName2);
                    if (ac2 != null) {
                        adm.removeAccountByName(ac2);
                    } else {
                        System.out.println("the account \"" + accountName2 + "\" dosnt exist");
                    }
                    break;
                default:
                    return;
            }
        }
    }

    private void save() {
        Store store = new Store(usersPool, accountsPool);
        String fileName = "data.txt";
        String dir = System.getProperty("user.dir") + "\\" + fileName;

        FileOutputStream fileOutput;
        try {
            fileOutput = new FileOutputStream(dir);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
            outputStream.writeObject(store);
            fileOutput.close();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("save error: " + e);
        }

    }

    private void read(){
        String fileName = "data.txt";
        String dir = System.getProperty("user.dir") + "\\" + fileName;
        try {
            FileInputStream fiStream = new FileInputStream(dir);
            ObjectInputStream objectStream = new ObjectInputStream(fiStream);
            Store store = (Store)objectStream.readObject();
            fiStream.close();
            objectStream.close();
            
            //now i am loading class accounts and users
            this.usersPool = store.users;
            this.accountsPool = store.accounts;
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("read error: " + e);
        }
    }

}
