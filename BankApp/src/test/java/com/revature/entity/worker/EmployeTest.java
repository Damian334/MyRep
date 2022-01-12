package com.revature.entity.worker;

import com.revature.app.Store;
import com.revature.entity.Account;
import com.revature.entity.Applicant;
import com.revature.entity.User;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmployeTest {

    private Set<User> usersPool = Store.usersPool;
    private Set<Account> accountsPool = Store.accountsPool;
    private List<Applicant> applicantPool = Store.applicantPool;

    @Before
    public void setUp() {
        usersPool.clear();
        accountsPool.clear();
        applicantPool.clear();
    }

    @Test
    public void testApproveAccounts() {
        User user = new User("name", "pass");
        usersPool.add(user);
        Account ac = new Account();
        ac.createAccount(user, "acName", true);// create account for user
        Employe e = new Employe(this.usersPool, this.accountsPool);
        assertEquals(e.approveAccounts("t", 1), true);
    }

    @Test
    public void testApproveAccountsFailed() {
        User user = new User("name", "pass");
        usersPool.add(user);
        Account ac = new Account();
        ac.createAccount(user, "acName", true);// create account for user        
        Employe e = new Employe(this.usersPool, this.accountsPool);
        assertEquals(e.approveAccounts("t", 5), false);//e.g select wrong index when there is only 1 record
    }
}
