package com.revature.app;

import com.revature.model.Account;
import com.revature.model.console.ConsoleAccount;
import com.revature.model.console.ConsoleUser;
import com.revature.model.worker.Admin;
import com.revature.model.console.ConsoleAdmin;
import com.revature.model.worker.Employe;
import java.util.Scanner;

public class Bank {

    public void run() {
        Scanner scn = new Scanner(System.in);
        //Store store = new Store();
        //store.read();//load at begin
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
                    //System.out.println("usersPool " + Store.usersPool.size() + " , accountsPool " + Store.accountsPool.size());
                    //store.save();//save at the end
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
                    ConsoleUser user = ConsoleUser.login();
                    System.out.println(user);
                    if (user != null) {
                        userLoged(scn, user);
                    } else {
                        System.out.println("your login or password is incorrect");
                        scn.nextLine();
                        return;
                    }
                    return;
                case "2":
                    ConsoleUser u2 = new ConsoleUser();
                    u2.registration();
                    return;
                default:
                    return;
            }
        }
    }

    private void userLoged(Scanner scn, ConsoleUser user) {
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
                    scn.nextLine();
                    break;
                case "2":
                    System.out.println(user.getUserConfirmedAccountInfo());//not working till i will add admin
                    scn.nextLine();
                    break;
                case "3":
                    String accountName = scn.nextLine();
                    Account a = user.getUserConfirmedAccount(accountName);
                    if (a != null) {
                        ConsoleAccount ac = new ConsoleAccount(a);
                        userLogedAccountOperations(scn, user, ac);
                    } else {
                        System.out.println("the account \"" + accountName + "\" dosnt exist");
                    }
                    scn.nextLine();
                    break;
                default:
                    return;
            }
        }
    }

    private void userLogedAccountOperations(Scanner scn, ConsoleUser user, ConsoleAccount ac) {
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
                    scn.nextLine();
                    break;
                case "2":
                    ac.withdraw(user);
                    scn.nextLine();
                    break;
                case "3":
                    ac.transfer(user);
                    scn.nextLine();
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
            Employe emp = new ConsoleAdmin();
            switch (nr) {
                case "1":
                    emp.showAccountsInformation();
                    scn.nextLine();
                    break;
                case "2":
                    emp.showAccountsBalances();
                    scn.nextLine();
                    break;
                case "3":
                    emp.showUsersInformation();
                    scn.nextLine();
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
            Admin adm = new ConsoleAdmin();
            switch (nr) {
                case "1":
                    System.out.println(adm.showAccountsInformation());
                    scn.nextLine();
                    break;
                case "2":
                    System.out.println(adm.showAccountsBalances());
                    scn.nextLine();
                    break;
                case "3":
                    System.out.println(adm.showUsersInformation());
                    scn.nextLine();
                    break;
                case "4":
                    adm.approveAccounts();
                    break;
                case "5":
                    ConsoleUser admin = new ConsoleUser("admin", "");
                    System.out.println("type account name:");
                    String accountName = scn.nextLine();
                    Account a = adm.getAccountByName(accountName);
                    if (a != null) {
                        ConsoleAccount ac = new ConsoleAccount(a);
                        userLogedAccountOperations(scn, admin, ac);
                    } else {
                        System.out.println("the account \"" + accountName + "\" dosnt exist");
                    }
                    break;
                case "6":
                    String accountName2 = scn.nextLine();
                    Account a2 = adm.getAccountByName(accountName2);
                    if (a2 != null) {
                        ConsoleAccount ac2 = new ConsoleAccount(a2);
                        adm.removeAccountByName(ac2.getAccountName());
                    } else {
                        System.out.println("the account \"" + accountName2 + "\" dosnt exist");
                    }
                    break;
                default:
                    return;
            }
        }
    }
}
