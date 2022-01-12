package com.revature.entity;

import com.revature.app.Store;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Account implements Serializable {

    private static final Logger logger = LogManager.getLogger(Account.class);
    public final Set<Account> accountsPool = Store.accountsPool;
    protected int id;
    protected double funds = 0;
    protected String accountName = null;
    protected Set<Applicant> applicants = new HashSet<Applicant>();
    private List<String> fundsOperations = new ArrayList<String>();

    public Account() {

    }

    //only for test
    public Account(int id, String accountName) {
        this.id = id;
        this.accountName = accountName;
        this.funds = funds;
    }

    public Object[] createAccount(User user, String name) {
        return createAccount(user, name, false);
    }

    public Object[] createAccount(User user, String name, boolean add) {
        Object[] msg = {false, "", null};
        if (name.contains(";")) {
            msg[1] = "name can't contain sing \";\"";
            return msg;
        }

        for (Account a : accountsPool) {
            if (a.accountName.equals(name)) {
                msg[1] = "account \"" + name + "\" already exist";
                return msg;
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
        if (add) {
            accountsPool.add(this);
        } else {
            msg[2] = this;
        }
        logger.info("account \"" + name + "\" is created");
        msg[0] = true;
        msg[1] = "account \"" + name + "\" is created";
        return msg;
    }

    //use when I want to add customer to existing account
    public Object[] addApplicant(User user, String name) {
        Object[] msg = {false, ""};
        for (Account a : accountsPool) {
            if (a.accountName.equals(name)) {
                Applicant ap = new Applicant(user);
                if (a.getApplicants().contains(ap)) {
                    msg[1] = "account " + name + " already have joint customer \"" + user.name + "\"";
                    return msg;
                }
                a.applicants.add(ap);
                logger.info(user.name + " joint the account \"" + name + "\"");
                msg[0] = true;
                msg[1] = user.name + " joint the account \"" + name + "\"";
                return msg;
            }
        }
        msg[1] = "account \"" + name + "\" dosn't exist";
        return msg;
    }

    @Override
    public String toString() {
        return "id: " + id + " , name: " + accountName + " , funds: " + funds;
    }

    public Object[] deposit(User user, Double money) {
        Object[] msg = {false, ""};
        if (money == null) {
            logger.error("deposit error for " + user.name);
            msg[1] = "error you typed wrong value";
        }
        if (money < 0) {
            msg[1] = "you can't deposit negative amounts";
            return msg;
        } else {
            this.funds += money;
            this.fundsOperations.add("user " + user.name + " deposited: " + money);
            logger.info("user " + user.name + " deposited: " + money);
            msg[0] = true;
            msg[1] = "user " + user.name + " deposited: " + money;
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
            this.fundsOperations.add("user " + user.name + " withdraw: " + money);
            logger.info("user " + user.name + " withdraw: " + money);
            msg[0] = true;
            msg[1] = "user " + user.name + " withdraw: " + money;
            return msg;
        }
    }

    public Object[] transfer(User user, Double money, String accName) {
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
            System.out.println("it is here?1");
            for (Account a : accountsPool) {
                if (a.getAccountName().equals(accName)) {
                    System.out.println("it is here?2");
                    a.funds += money;
                    this.funds -= money;
                    this.fundsOperations.add("user " + user.name + " transfered: " + money + " to " + a.getAccountName());
                    a.fundsOperations.add("user " + user.name + " transfered: " + money + " to " + a.getAccountName());
                    logger.info("user " + user.name + " transfered: " + money + " to " + a.getAccountName());

                    System.out.println("it is here?3");
                    msg[0] = true;
                    msg[1] = "user " + user.name + " transfered: " + money + " to " + a.getAccountName();
                    return msg;
                }
            }
            msg[1] = "the account \"" + accName + "\" dosn't exist";
            return msg;
        }
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
