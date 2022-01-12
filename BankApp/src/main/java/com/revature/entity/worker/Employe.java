package com.revature.entity.worker;

import com.revature.entity.Account;
import com.revature.entity.Applicant;
import com.revature.entity.User;
import java.util.HashSet;
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
    }

    public void showAccountsInformation() {
        for (Account a : accountsPool) {
            System.out.println(a.toString());
        }
    }

    public String showAccountsBalances() {
        String msg = "";
        for (Account a : accountsPool) {
            msg += a.toString() + " , balances:\n";
            for (String s : a.getFundsOperations()) {
                System.out.println(s);
            }
        }
        return msg;
    }

    public void showUsersInformation() {
        for (User user : usersPool) {
            System.out.println(user.toString());
        }
    }

    public boolean approveAccounts() {
        return false;
    }

    public String approveShowAccounts() {
        String msg = "";
        msg += "approve/deny open applications for accounts by selecting nr and write t/n e.g \"1 t\":\n";
        for (Account ac : accountsPool) {
            for (Applicant ap : ac.getApplicants()) {
                if (!ap.getStatus()) {
                    msg += ap.getId() + ". customer name: " + ap.getUser().name + " , account name: " + ac.getAccountName()
                            + " , funds: " + ac.getFunds() + "\n";
                }
            }
        }
        return msg;
    }

    public boolean approveAccounts(String str, int nr) {
        for (Account ac : accountsPool) {
            for (Applicant ap : ac.getApplicants()) {
                if (!ap.getStatus()) {
                    if (ap.getId() == nr) {
                        if (str.equals("t")) {
                            ap.accept();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    protected Logger getLogger() {
        return logger;
    }
}