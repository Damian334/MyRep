package com.revature.entity;

import com.revature.app.Bank;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {

    private Set<User> usersPool = Bank.usersPool;
    private Set<Account> accountsPool = Bank.accountsPool;

    @Before
    public void setUp() {
        usersPool.clear();
        accountsPool.clear();
    }

    @Test
    public void testCreateAccount() {
        InputStream sysInBackup = System.in;
        User user = new User("name", "pass");
        Account ac = new Account();

        System.setIn(new ByteArrayInputStream("acName".getBytes()));
        assertEquals(ac.createAccount(user), true);
        System.setIn(sysInBackup);
    }

    @Test
    public void testCreateAccountFailed() {
        InputStream sysInBackup = System.in;
        User user = new User("name", "pass");
        Account ac = new Account();

        System.setIn(new ByteArrayInputStream("acName".getBytes()));
        ac.createAccount(user);//first i am creating an account ,and later tring to do it again        

        System.setIn(new ByteArrayInputStream("acName".getBytes()));
        assertEquals(ac.createAccount(user), false);
        System.setIn(sysInBackup);
    }

    @Test
    public void testAddApplicant() {
        InputStream sysInBackup = System.in;
        User user = new User("name", "pass");
        Account ac = new Account();
        System.setIn(new ByteArrayInputStream("acName".getBytes()));
        ac.createAccount(user);

        System.setIn(new ByteArrayInputStream("acName".getBytes()));
        User user2 = new User("name2", "pass2");

        assertEquals(ac.addApplicant(user2), true);//add other customer to account
        assertEquals(ac.getApplicants().size(), 2);//and account get 2 potential customers        
        System.setIn(sysInBackup);
    }

    @Test
    public void testAddApplicantFailed() {
        InputStream sysInBackup = System.in;
        User user = new User("name", "pass");
        Account ac = new Account();
        System.setIn(new ByteArrayInputStream("acName".getBytes()));
        ac.createAccount(user);

        System.setIn(new ByteArrayInputStream("acName".getBytes()));
        assertEquals(ac.addApplicant(user), false);
        System.setIn(sysInBackup);
    }

    @Test
    public void testDeposit() {
        InputStream sysInBackup = System.in;
        User user = new User("userName", "pass");
        Account ac = new Account();
        System.setIn(new ByteArrayInputStream("acName".getBytes()));
        ac.createAccount(user);

        System.setIn(new ByteArrayInputStream("1000".getBytes()));
        ac.deposit(user);

        assertEquals(ac.getFunds() == 1000f, true);
        System.setIn(sysInBackup);
    }

    @Test
    public void testDepositFailed() {
        InputStream sysInBackup = System.in;
        User user = new User("userName", "pass");
        Account ac = new Account();
        System.setIn(new ByteArrayInputStream("acName".getBytes()));
        ac.createAccount(user);

        System.setIn(new ByteArrayInputStream("-1000".getBytes()));
        ac.deposit(user);

        assertEquals(ac.getFunds() == 0f, true);
        System.setIn(sysInBackup);
    }
    
    @Test
    public void testWithdraw() {
        InputStream sysInBackup = System.in;
        User user = new User("userName", "pass");
        Account ac = new Account();
        System.setIn(new ByteArrayInputStream("acName".getBytes()));
        ac.createAccount(user);

        System.setIn(new ByteArrayInputStream("1000".getBytes()));
        ac.deposit(user);
                
        System.setIn(new ByteArrayInputStream("500".getBytes()));
        assertEquals(ac.withdraw(user),true);
        assertEquals(ac.getFunds() == 1000f-500f, true);
        System.setIn(sysInBackup);
    }
    
    @Test
    public void testWithdrawFail() {
        InputStream sysInBackup = System.in;
        User user = new User("userName", "pass");
        Account ac = new Account();
        System.setIn(new ByteArrayInputStream("acName".getBytes()));
        ac.createAccount(user);

        System.setIn(new ByteArrayInputStream("1000".getBytes()));
        ac.deposit(user);
                
        System.setIn(new ByteArrayInputStream("2000".getBytes()));
        assertEquals(ac.withdraw(user), false);
        System.setIn(sysInBackup);
    }
    
    @Test
    public void testTransfer(){        
        InputStream sysInBackup = System.in;
        User user = new User("userName", "pass");
        Account ac = new Account();
        System.setIn(new ByteArrayInputStream("acName".getBytes()));
        ac.createAccount(user);
                
        Account ac2 = new Account();
        System.setIn(new ByteArrayInputStream("acOther".getBytes()));
        ac2.createAccount(user);        
        
        System.setIn(new ByteArrayInputStream("1000".getBytes()));
        ac.deposit(user);
        
        System.setIn(new ByteArrayInputStream("200\nacOther".getBytes()));
        assertEquals(ac.transfer(user),true);
        assertEquals(ac.getFunds()==800f,true);
        assertEquals(ac2.getFunds()==200f,true);
        System.setIn(sysInBackup);
    }
    
    @Test
    public void testTransferOnNotExistingAccount(){        
        InputStream sysInBackup = System.in;
        User user = new User("userName", "pass");
        Account ac = new Account();
        System.setIn(new ByteArrayInputStream("acName".getBytes()));
        ac.createAccount(user);
        System.setIn(new ByteArrayInputStream("1000".getBytes()));
        ac.deposit(user);
        
        System.setIn(new ByteArrayInputStream("200\nacOther".getBytes()));
        assertEquals(ac.transfer(user),false);
        assertEquals(ac.getFunds()==1000f,true);
        System.setIn(sysInBackup);
    }
    
}
