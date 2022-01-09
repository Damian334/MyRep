package com.revature.entity.worker;

import com.revature.app.Bank;
import com.revature.entity.Account;
import com.revature.entity.User;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmployeTest {    

    private Set<User> usersPool = Bank.usersPool;
    private Set<Account> accountsPool = Bank.accountsPool;

    @Before
    public void setUp() {
        usersPool.clear();
        accountsPool.clear();
    }

    @Test
    public void testApproveAccounts() {        
        InputStream sysInBackup = System.in;
        
        User user = new User("name","pass");
        usersPool.add(user);
        
        System.setIn(new ByteArrayInputStream("acName".getBytes()));
        Account ac = new Account();
        ac.createAccount(user);// create account for user
        
        System.setIn(new ByteArrayInputStream("1 t".getBytes()));
        Employe e = new Employe(this.usersPool,this.accountsPool);
        assertEquals(e.approveAccounts(),true);       
        System.setIn(sysInBackup);
    }
    
    @Test
    public void testApproveAccountsFailed() {        
        InputStream sysInBackup = System.in;
        
        User user = new User("name","pass");
        usersPool.add(user);
        
        System.setIn(new ByteArrayInputStream("acName".getBytes()));
        Account ac = new Account();
        ac.createAccount(user);// create account for user
        
        System.setIn(new ByteArrayInputStream("5 t".getBytes()));//e.g select wrong index when there is only 1 record
        Employe e = new Employe(this.usersPool,this.accountsPool);
        assertEquals(e.approveAccounts(),false);       
        System.setIn(sysInBackup);
    }
}
