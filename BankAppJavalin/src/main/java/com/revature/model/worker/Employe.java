package com.revature.model.worker;

import com.revature.model.Account;
import com.revature.model.User;
import com.revature.model.dao.AccountDao;
import com.revature.model.dao.ApplicantDao;
import com.revature.model.dao.FundsOperationsDao;
import com.revature.model.dao.UserDao;
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

    public String showAccountsInformation() {
        String msg = "";
        for (Account a : accDao.getAllAccounts()) {
            msg += a.toString() + "\n";
        }
        return msg;
    }

    public String showAccountsBalances() {
        String msg = "";
        for (Account account : accDao.getAllAccounts()) {
            msg += account.toString() + " , funds operations:\n";
            for (String s : operationDao.getOperations(account.getAccountName())) {
                msg += s + "\n";
            }
        }
        return msg;
    }

    public String showUsersInformation() {
        String msg = "";
        for (User user : userDao.getAllUsers()) {
            msg += user.toString() + "\n";
        }
        return msg;
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
