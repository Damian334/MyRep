package com.revature.model;

import com.revature.model.dao.AccountDao;
import com.revature.model.dao.UserDao;
import java.io.Serializable;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Customer
public class User implements Serializable {

    protected static final Logger logger = LogManager.getLogger(User.class);
    protected static UserDao userDao = new UserDao();
    protected static AccountDao accDao = new AccountDao();
    protected int id;
    protected String name = null;
    protected String password = null;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    
    public User(int id, String name, String password) {
        this.id=id;
        this.name = name;
        this.password = password;
    }
        
    public Object[] registration(String name, String password) {
        Object[] msg = {false, ""};
        if(name.trim().equals("")){
            msg[1] = "name can't be empty";
            return msg;
        }        
        boolean res = userDao.addUser(name, password);
        if(res){
            msg[1] = "user \"" + name + "\" get created";
            logger.info("user \"" + name + "\" get created");  
        }else{
            msg[1] = "user \"" + name + "\" already exist";
        }
        msg[0] = res;
        return msg;//return true when user get created
    }

    public static User login(String name, String password) {
        return userDao.getLogin(name, password);
    }

    //not tested because it could be double later because of switch
    public Object[] addAccountToUser(Integer nr, String accountName) {
        Account ac = new Account();
        switch (nr) {
            case 1:
                return ac.createAccount(this, accountName);
            case 2:
                return ac.addApplicant(this, accountName);
            default:
                return null;
        }
    }

    public String getUserConfirmedAccountInfo() {//showing accounts of THIS user
        List<Account> list = accDao.getAccounts(this.name,true);
        String str = "user confirmed accounts:\n";
        for (Account a : list) {
            str += "Account name: " + a.getAccountName() + " , funds: " + a.getFunds() + "\n";
        }        
        return str;
    }

    public Account getUserConfirmedAccount(String accountName) {//show only 1 account for THIS user with status=true
        return accDao.getAccount(accountName,true);
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
    
    
    @Override
    public String toString() {
        return "id: "+id +", name: " + name + " , password: " + password;
    }
    
}
