package com.revature.entity.worker;

import com.revature.entity.Account;
import com.revature.entity.Applicant;
import com.revature.entity.User;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Admin extends Employe {

    private static final Logger logger = LogManager.getLogger(Admin.class);
    
    public Admin(Set<User> usersPool, Set<Account> accountsPool) {
        super(usersPool, accountsPool);
    }

    public Account getAccountByName(String accName) {
        for (Account a : accountsPool) {
            if (a.getAccountName().equals(accName)) {
                return a;
            }
        }
        return null;
    }

    public boolean removeAccountByName(Account ac) {
        if(!this.accountsPool.contains(ac)){
            return false;
        }
        Set<Applicant> aps = ac.getApplicants();
        for (Applicant a : aps) {
            a.getUser().getUserAccounts().remove(ac);
        }
        System.out.println("account "+ac.getAccountName()+" was removed");
        accountsPool.remove(ac);
        return true;
    }

}
