package com.revature.entity.worker;

import com.revature.app.Bank;
import com.revature.entity.Account;
import com.revature.entity.User;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AdminTest {

    private Set<User> usersPool = Bank.usersPool;
    private Set<Account> accountsPool = Bank.accountsPool;

    @Before
    public void setUp() {
        usersPool.clear();
        accountsPool.clear();
    }

    @Test
    public void testRemoveAccountByName() { 
        InputStream sysInBackup = System.in;        
        User user = new User("name","pass");
        usersPool.add(user);
        
        System.setIn(new ByteArrayInputStream("acName".getBytes()));
        Account ac = new Account();
        assertEquals(ac.createAccount(user),true);// create account for user
        assertEquals(this.accountsPool.size(),1);
        
        Admin admin = new Admin(this.usersPool,this.accountsPool);
        assertEquals(admin.removeAccountByName(ac),true);
        assertEquals(this.accountsPool.size(),0);
        System.setIn(sysInBackup);        
    }
        
    @Test
    public void testRemoveAccountByNameFailed() { 
        InputStream sysInBackup = System.in;        
        User user = new User("name","pass");
        usersPool.add(user);
        
        System.setIn(new ByteArrayInputStream("acName".getBytes()));
        Account ac = new Account();
        assertEquals(ac.createAccount(user),true);// create account for user
        assertEquals(this.accountsPool.size(),1);
                
        Account ac2 = new Account(10,"not existing acc");
        Admin admin = new Admin(this.usersPool,this.accountsPool);
        assertEquals(admin.removeAccountByName(ac2),false);
        assertEquals(this.accountsPool.size(),1);
        System.setIn(sysInBackup);        
    }
}
