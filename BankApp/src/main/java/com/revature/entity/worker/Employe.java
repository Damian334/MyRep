package com.revature.entity.worker;

import com.revature.entity.Account;
import com.revature.entity.Applicant;
import com.revature.entity.User;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Employe {

    private static final Logger logger = LogManager.getLogger(Employe.class);
    protected Set<User> usersPool = new HashSet<>();
    protected Set<Account> accountsPool = new HashSet<>();

    public Employe(Set<User> usersPool, Set<Account> accountsPool) {
        this.usersPool = usersPool;
        this.accountsPool = accountsPool;
    }

    public Employe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void showAccountsInformation() {
        for (Account a : accountsPool) {
            System.out.println(a.toString());
        }
    }

    public void showAccountsBalances() {
        for (Account a : accountsPool) {
            System.out.println(a.toString() + " , balances:");
            for (String s : a.getFundsOperations()) {
                System.out.println(s);
            }
        }
    }

    public void showUsersInformation() {
        for (User user : usersPool) {
            System.out.println(user.toString());
        }
    }

    public boolean approveAccounts() {
        Scanner scn = new Scanner(System.in);
        int i = 1;
        System.out.println("approve/deny open applications for accounts by selecting nr and write t/n e.g \"1 t\":");
        for (Account ac : accountsPool) {
            for (Applicant ap : ac.getApplicants()) {
                if (!ap.getStatus()) {
                    System.out.println(i + ". customer name: " + ap.getUser().name + " , account name: " + ac.getAccountName()
                            + " , funds: " + ac.getFunds());
                    i++;
                }
            }
        }
        try {
            String[] s = scn.nextLine().trim().split(" ");
            i=1;
            for (Account ac : accountsPool) {
                for (Applicant ap : ac.getApplicants()) {
                    if (!ap.getStatus()) { 
                        if(i==Integer.valueOf(s[0])){
                            if(s[1].equals("t")){
                                ap.accept();
                                return true;
                            }
                        }
                        i++;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
            logger.info("Emplye approveAccounts error: "+e);
        }
        return false;
    }

}
