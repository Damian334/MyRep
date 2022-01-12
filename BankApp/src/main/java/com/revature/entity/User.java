package com.revature.entity;

import com.revature.app.Store;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Customer
public class User implements Serializable {

    protected static final Logger logger = LogManager.getLogger(User.class);
    public int id;
    public String name = null;
    public String password = null;
    protected Set<Account> accountsPool = Store.accountsPool;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Object[] registration(Set<User> usersPool, String name, String password) {
        return registration(usersPool, name, password, false);
    }

    public Object[] registration(Set<User> usersPool, String name, String password, boolean add) {
        this.name = name;
        this.password = password;
        Object[] msg = {false, "", null};

        if (name.contains(";") || password.contains(";")) {
            msg[1] = "name or password can't contain sing \";\"";
            return msg;
        }

        boolean check = false;
        for (User u : usersPool) {
            if (u.name.equals(name)) {
                check = true;
                break;
            }
        }
        if (check) {
            msg[1] = "user already exist";
        } else {
            int i = 1;
            for (User us : usersPool) {
                if (us.id >= i) {
                    i = us.id + 1;
                }
            }
            this.id = i;
            if (add) {
                usersPool.add(this);
            } else {
                msg[2] = this;
            }
            msg[1] = "user \"" + this.name + "\" get created";
            logger.info("user \"" + this.name + "\" get created");
        }
        msg[0] = !check;
        return msg;//return true when user get created
    }

    public static User login(Set<User> usersPool, String name, String password) {
        for (User user : usersPool) {
            if (user.name.equals(name) && user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }

    //not tested because it could be double later because of switch
    public Object[] addAccountToUser(Integer nr, String err, String name) {
        Account ac = new Account();
        switch (nr) {
            case 1:
                return ac.createAccount(this, name);
            case 2:
                return ac.addApplicant(this, name);
            default:
                if (err == null) {
                    logger.error("addAccountToUser for user " + this.name + " error: " + err);
                }
                return null;
        }
    }

    @Override
    public String toString() {
        return "name: " + name + " , password: " + password;
    }

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
