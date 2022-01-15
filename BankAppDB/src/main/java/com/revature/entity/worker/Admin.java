package com.revature.entity.worker;

import com.revature.entity.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Admin extends Employe {

    private static final Logger logger = LogManager.getLogger(Admin.class);

    public Admin() {
        super();
    }

    public Account getAccountByName(String accName) {
        return accDao.getAccount(accName);
    }

    public boolean removeAccountByName(String accountName) {
        if (accDao.deleteAccount(accountName) == 0) {
            return false;
        }
        logger.info("account " + accountName + " was removed");
        return true;
    }
}
