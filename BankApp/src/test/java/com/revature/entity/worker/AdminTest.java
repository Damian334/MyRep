package com.revature.entity.worker;

import com.revature.app.Store;
import com.revature.entity.Account;
import com.revature.entity.Applicant;
import com.revature.entity.User;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AdminTest {

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
    public void testRemoveAccountByName() {
        User user = new User("name", "pass");
        usersPool.add(user);
        Account ac = new Account();
        Object[] res = ac.createAccount(user, "acName", true);
        assertEquals((boolean) res[0], true);// create account for user
        assertEquals(this.accountsPool.size(), 1);

        Admin admin = new Admin(this.usersPool, this.accountsPool);
        assertEquals(admin.removeAccountByName(ac), true);
        assertEquals(this.accountsPool.size(), 0);
    }

    @Test
    public void testRemoveAccountByNameFailed() {
        User user = new User("name", "pass");
        usersPool.add(user);
        Account ac = new Account();
        Object[] res = ac.createAccount(user, "acName", true);
        assertEquals((boolean) res[0], true);// create account for user
        assertEquals(this.accountsPool.size(), 1);

        Account ac2 = new Account(10, "not existing acc");
        Admin admin = new Admin(this.usersPool, this.accountsPool);
        assertEquals(admin.removeAccountByName(ac2), false);
        assertEquals(this.accountsPool.size(), 1);
    }
}
