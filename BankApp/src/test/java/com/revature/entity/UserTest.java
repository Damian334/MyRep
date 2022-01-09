package com.revature.entity;

import com.revature.app.Bank;
import com.revature.entity.worker.Employe;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    private Set<User> usersPool = Bank.usersPool;
    private Set<Account> accountsPool = Bank.accountsPool;
    
    @Before
    public void setUp() {
        usersPool.clear();
        accountsPool.clear();
    }
            
    @Test
    public void testRegistration() {
        InputStream sysInBackup = System.in;        
        System.setIn(new ByteArrayInputStream("name\npass".getBytes()));        
        User user = new User();
        boolean result = user.registration(usersPool);
        assertEquals(result, true);
        System.setIn(sysInBackup);
    }
    
    @Test
    public void testRegistrationFailed() {
        InputStream sysInBackup = System.in;        
        System.setIn(new ByteArrayInputStream("name\npass".getBytes()));        
        User user = new User();
        usersPool.add(new User("name","pass"));
        boolean result = user.registration(usersPool);
        assertEquals(result, false);
        System.setIn(sysInBackup);
    }
        
    @Test
    public void testLogin(){
        InputStream sysInBackup = System.in;        
        System.setIn(new ByteArrayInputStream("name2\npass2".getBytes()));        
        User user = new User("name2","pass2");
        usersPool.add(new User("name","pass"));
        usersPool.add(user);
        User result = User.login(usersPool);        
        
        assertEquals(result, user);
        System.setIn(sysInBackup);
    }
    
    @Test
    public void testLoginFailed(){
        InputStream sysInBackup = System.in;        
        System.setIn(new ByteArrayInputStream("alien\npass".getBytes()));                
        usersPool.add(new User("name","pass"));
        usersPool.add(new User("name2","pass2"));
        User result = User.login(usersPool);        
        
        assertEquals(result, null);
        System.setIn(sysInBackup);
    }

    @Test
    public void testGetUserAccounts(){
        InputStream sysInBackup = System.in;
        
        User user = new User("name","pass");
        usersPool.add(user);
        
        System.setIn(new ByteArrayInputStream("acName".getBytes()));
        Account ac = new Account();
        ac.createAccount(user);// create account for user
        
        System.setIn(new ByteArrayInputStream("acName2".getBytes()));        
        Account ac2 = new Account();
        ac2.createAccount(user);// create 2nd account for user
        
        assertEquals(user.getUserAccounts().size(), 2);        
        System.setIn(sysInBackup);
    }
    
    @Test
    public void testGetUserAccountsFailed(){
        InputStream sysInBackup = System.in;
        
        User user = new User("name","pass");
        usersPool.add(user);
        
        System.setIn(new ByteArrayInputStream("acName".getBytes()));
        Account ac = new Account();
        ac.createAccount(user);// create account for user
        
        System.setIn(new ByteArrayInputStream("acName".getBytes()));        
        Account ac2 = new Account();
        ac2.createAccount(user);// create 2nd account for user
        
        assertEquals(user.getUserAccounts().size(), 1);//the user will create only 1 account    
        System.setIn(sysInBackup);
    }
    
    @Test
    public void testGetUserConfirmedAccounts(){
        InputStream sysInBackup = System.in;
        
        User user = new User("name","pass");
        usersPool.add(user);
        
        System.setIn(new ByteArrayInputStream("acName".getBytes()));
        Account ac = new Account();
        ac.createAccount(user);// create account for user
        
        System.setIn(new ByteArrayInputStream("1 t".getBytes()));
        Employe e = new Employe(this.usersPool,this.accountsPool);
        e.approveAccounts();
                
        assertEquals(user.getUserConfirmedAccounts().size(), 1);        
        System.setIn(sysInBackup);
    }
    
    @Test
    public void testGetUserConfirmedAccount(){
        InputStream sysInBackup = System.in;
        
        User user = new User("name","pass");
        usersPool.add(user);
        
        System.setIn(new ByteArrayInputStream("acName".getBytes()));
        Account ac = new Account();
        ac.createAccount(user);// create account for user
        
        System.setIn(new ByteArrayInputStream("1 t".getBytes()));
        Employe e = new Employe(this.usersPool,this.accountsPool);
        e.approveAccounts();
                
        assertEquals(user.getUserConfirmedAccount("acName"), ac);        
        System.setIn(sysInBackup);
    }
    
    @Test
    public void testGetUserConfirmedAccountFailed(){
        InputStream sysInBackup = System.in;
        
        User user = new User("name","pass");
        usersPool.add(user);
        
        System.setIn(new ByteArrayInputStream("acName".getBytes()));
        Account ac = new Account();
        ac.createAccount(user);// create account for user
        //account wasn't confirment by employe or admin
        assertEquals(user.getUserConfirmedAccount("acName"), null);        
        System.setIn(sysInBackup);
    }
    
}
