package com.revature.model;

import com.revature.model.dao.AccountDao;
import com.revature.model.dao.ApplicantDao;
import com.revature.model.dao.FundsOperationsDao;
import java.io.Serializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Account implements Serializable {

    private static final Logger logger = LogManager.getLogger(Account.class);
    protected static AccountDao accDao = new AccountDao();
    protected static ApplicantDao appDao = new ApplicantDao();
    protected static FundsOperationsDao operationDao = new FundsOperationsDao();
    protected int id;
    protected double funds = 0;
    protected String accountName = null;

    public Account() {

    }

    //only for test
    public Account(int id, String accountName) {
        this.id = id;
        this.accountName = accountName;
    }

    //for db leter check if i need others
    public Account(int id, double funds, String accountName) {
        this.id = id;
        this.funds = funds;
        this.accountName = accountName;
    }

    public Object[] createAccount(User user, String accountName) {
        Object[] msg = {false, ""};
        if (accountName.trim().equals("")) {
            msg[1] = "accountName can't be empty";
            return msg;
        }
        boolean res = accDao.addAccount(accountName, user);
        if (res) {
            msg[1] = "account \"" + accountName + "\" got created";
            logger.info("account \"" + accountName + "\" got created");
        } else {
            msg[1] = "account \"" + accountName + "\" already exist";
        }
        msg[0] = res;
        return msg;
    }

    //use when I want to add customer to existing account
    public Object[] addApplicant(User user, String accountName) {
        Object[] msg = {false, ""};
        int accountId = accDao.getIdByName(accountName);
        Applicant applicant = new Applicant(accountId, user.id);
        boolean res = appDao.addApplicant(applicant, accountName);
        if (res) {
            msg[1] = user.name + " joint the account \"" + accountName + "\"";
            logger.info(user.name + " joint the account \"" + accountName + "\"");
        } else {
            msg[1] = "account " + accountName + " already have joint customer \"" + user.name + "\"";
        }
        msg[0] = res;
        return msg;
    }

    public Object[] deposit(User user, Double money) {
        Object[] msg = {false, ""};
        if (money == null) {
            logger.error("deposit error for " + user.name);
            msg[1] = "error you typed wrong value";
            return msg;
        }
        if (money < 0) {
            msg[1] = "you can't deposit negative amounts";
            return msg;
        } else {
            this.funds += money;
            accDao.setFunds(accountName, this.funds);
            String info = "the account: " + accountName + ", user: " + user.name + ", deposited: " + money;
            logger.info(info);
            operationDao.addOperation(id, info);
            msg[0] = true;
            msg[1] = info;
            return msg;
        }
    }

    public Object[] withdraw(User user, Double money) {
        Object[] msg = {false, ""};
        if (money == null) {
            logger.error("deposit error for " + user.name);
            msg[1] = "error you typed wrong value";
        }
        if (money < 0) {
            msg[1] = "you can't withdraw negative amounts";
            return msg;
        } else if (this.funds < money) {
            msg[1] = "there is no such amount of funds in this account";
            return msg;
        } else {
            this.funds -= money;
            accDao.setFunds(accountName, this.funds);
            String info = "the account: " + accountName + ", user: " + user.name + " withdraw: " + money;
            logger.info(info);
            operationDao.addOperation(id, info);
            msg[0] = true;
            msg[1] = info;
            return msg;
        }
    }

    public Object[] transfer(User user, Double money, String otherAccName) {
        Object[] msg = {false, ""};
        if (money == null) {
            logger.error("deposit error for " + user.name);
            msg[1] = "error you typed wrong value";
        }
        if (money < 0) {
            msg[1] = "you can't withdraw negative amounts";
            return msg;
        } else if (this.funds < money) {
            msg[1] = "there is no such amount of funds on this account";
            return msg;
        } else {
            if (Account.accDao.isAccountNameTaken(otherAccName)) {
                this.funds -= money;
                double otherFunds = accDao.getFunds(otherAccName) + money;
                accDao.setFunds(accountName, this.funds);
                accDao.setFunds(otherAccName, otherFunds);
                String info = "at account: " + accountName + ", user: " + user.name + " transfered: " + money + " to other user: " + otherAccName;
                logger.info(info);
                operationDao.addOperation(id, info);
                msg[0] = true;
                msg[1] = info;
                return msg;
            } else {
                msg[1] = "the account \"" + otherAccName + "\" dosn't exist";
            }
            return msg;
        }
    }

    public int getId() {
        return id;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public double getFunds() {
        return this.funds;
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
    
    @Override
    public String toString() {
        return "id: " + id + " , name: " + accountName + " , funds: " + funds;
    }

}
