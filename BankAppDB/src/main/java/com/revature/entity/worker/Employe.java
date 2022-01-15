package com.revature.entity.worker;

import com.revature.entity.Account;
import com.revature.entity.User;
import com.revature.entity.dao.AccountDao;
import com.revature.entity.dao.ApplicantDao;
import com.revature.entity.dao.FundsOperationsDao;
import com.revature.entity.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Employe {

    private static final Logger logger = LogManager.getLogger(Employe.class);
    protected static UserDao userDao = new UserDao();
    protected static AccountDao accDao = new AccountDao();
    protected static ApplicantDao appDao = new ApplicantDao();
    protected static FundsOperationsDao operationDao = new FundsOperationsDao();

    public Employe() {
    }

    public void showAccountsInformation() {
        for (Account a : accDao.getAllAccounts()) {
            System.out.println(a.toString());
        }
    }

    public String showAccountsBalances() {
        String msg = "";
        for (Account account : accDao.getAllAccounts()) {
            msg += account.toString() + " , funds operations:\n";
            for (String s : operationDao.getOperations(account.getAccountName())) {
                System.out.println(s);
            }
        }
        return msg;
    }

    public void showUsersInformation() {
        for (User user : userDao.getAllUsers()) {
            System.out.println(user.toString());
        }
    }

    public boolean approveAccounts() {//only for polymorphism with ConsoleAdmin
        return false;
    }

    public String approveShowAccounts() {
        String msg = "";
        msg += "approve/deny open applications for accounts by selecting nr and write t/n e.g \"1 t\":\n";
        for (String[] info : appDao.getApplicantsInfo()) {
            msg += info[0] + ". customer name: " + info[1] + " , account name: " + info[2] + " , funds: " + info[3] + "\n";
        }
        return msg;
    }

    public boolean approveAccounts(String str, int nr) {
        if (str.equals("t")) {
            return appDao.acceptById(nr);
        }
        return false;
    }

    protected Logger getLogger() {
        return logger;
    }
}
