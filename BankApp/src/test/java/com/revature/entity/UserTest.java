package com.revature.entity;

import com.revature.app.Store;
import com.revature.entity.worker.Employe;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

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
    public void testRegistration() {
        User user = new User();
        Object[] ob = user.registration(usersPool, "name", "pass", true);
        boolean result = (boolean) ob[0];
        assertEquals(result, true);
    }

    @Test
    public void testRegistrationFailed() {
        User user = new User();
        usersPool.add(new User("name", "pass"));
        Object[] ob = user.registration(usersPool, "name", "pass", true);
        boolean result = (boolean) ob[0];
        assertEquals(result, false);
    }

    @Test
    public void testLogin() {
        User user = new User("name2", "pass2");
        usersPool.add(new User("name", "pass"));
        usersPool.add(user);
        User result = User.login(usersPool, "name2", "pass2");
        assertEquals(result, user);
    }

    @Test
    public void testLoginFailed() {
        usersPool.add(new User("name", "pass"));
        usersPool.add(new User("name2", "pass2"));
        User result = User.login(usersPool, "alien", "pass");
        assertEquals(result, null);
    }

    @Test
    public void testGetUserAccounts() {
        User user = new User("name", "pass");
        usersPool.add(user);
        Account ac = new Account();
        ac.createAccount(user, "acName", true);// create account for user     
        Account ac2 = new Account();
        ac2.createAccount(user, "acName2", true);// create 2nd account for user        
        assertEquals(user.getUserAccounts().size(), 2);
    }

    @Test
    public void testGetUserAccountsFailed() {
        User user = new User("name", "pass");
        usersPool.add(user);
        Account ac = new Account();
        ac.createAccount(user, "acName", true);// create account for user      
        Account ac2 = new Account();
        ac2.createAccount(user, "acName", true);// create 2nd account for user        
        assertEquals(user.getUserAccounts().size(), 1);//the user will create only 1 account
    }

    @Test
    public void testGetUserConfirmedAccounts() {
        User user = new User("name", "pass");
        usersPool.add(user);
        Account ac = new Account();
        ac.createAccount(user, "acName", true);// create account for user
        Employe e = new Employe(this.usersPool, this.accountsPool);
        assertEquals(e.approveAccounts("t", 1), true);
        assertEquals(user.getUserConfirmedAccounts().size(), 1);
    }

    @Test
    public void testGetUserConfirmedAccount() {
        User user = new User("name", "pass");
        usersPool.add(user);
        Account ac = new Account();
        ac.createAccount(user, "acName", true);// create account for user
        Employe e = new Employe(this.usersPool, this.accountsPool);
        assertEquals(e.approveAccounts("t", 1), true);
        assertEquals(user.getUserConfirmedAccount("acName"), ac);
    }

    @Test
    public void testGetUserConfirmedAccountFailed() {
        User user = new User("name", "pass");
        usersPool.add(user);
        Account ac = new Account();
        ac.createAccount(user, "acName", true);//create account for user
        //account wasn't confirment by employe or admin
        assertEquals(user.getUserConfirmedAccount("acName"), null);
    }
}
