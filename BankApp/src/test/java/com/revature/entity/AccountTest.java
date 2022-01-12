package com.revature.entity;

import com.revature.app.Store;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {

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
    public void testCreateAccount() {
        User user = new User("name", "pass");
        Account ac = new Account();
        Object[] res = ac.createAccount(user, "acName", true);
        assertEquals((boolean) res[0], true);
    }

    @Test
    public void testCreateAccountFailed() {
        User user = new User("name", "pass");
        Account ac = new Account();
        ac.createAccount(user, "acName", true);//first i am creating an account ,and later tring to do it again  
        Object[] res = ac.createAccount(user, "acName", true);
        assertEquals((boolean) res[0], false);
    }

    @Test
    public void testAddApplicant() {
        User user = new User("name", "pass");
        Account ac = new Account();
        ac.createAccount(user, "acName", true);
        User user2 = new User("name2", "pass2");
        Object[] res = ac.addApplicant(user2, "acName");
        assertEquals((boolean) res[0], true);//add other customer to account
        assertEquals(ac.getApplicants().size(), 2);//and account get 2 potential customers
    }

    @Test
    public void testAddApplicantFailed() {
        User user = new User("name", "pass");
        Account ac = new Account();
        ac.createAccount(user, "acName", true);
        Object[] res = ac.addApplicant(user, "acName");
        assertEquals((boolean) res[0], false);
    }

    @Test
    public void testDeposit() {
        User user = new User("userName", "pass");
        Account ac = new Account();
        ac.createAccount(user, "acName", true);
        ac.deposit(user, 1000d);
        assertEquals(ac.getFunds() == 1000d, true);
    }

    @Test
    public void testDepositFailed() {
        User user = new User("userName", "pass");
        Account ac = new Account();
        ac.createAccount(user, "acName", true);
        ac.deposit(user, -1000d);
        assertEquals(ac.getFunds() == 0d, true);
    }

    @Test
    public void testWithdraw() {
        User user = new User("userName", "pass");
        Account ac = new Account();
        ac.createAccount(user, "acName", true);
        ac.deposit(user, 1000d);
        Object[] res = ac.withdraw(user, 500d);
        assertEquals((boolean) res[0], true);
        assertEquals(ac.getFunds() == 1000f - 500d, true);
    }

    @Test
    public void testWithdrawFail() {
        User user = new User("userName", "pass");
        Account ac = new Account();
        ac.createAccount(user, "acName", true);
        ac.deposit(user, 1000d);
        Object[] res = ac.withdraw(user, 2000d);
        assertEquals((boolean) res[0], false);
    }

    @Test
    public void testTransfer() {
        User user = new User("userName", "pass");
        Account ac = new Account();
        ac.createAccount(user, "acName", true);
        Account ac2 = new Account();
        ac2.createAccount(user, "acOther", true);
        ac.deposit(user, 1000d);
        Object[] res = ac.transfer(user, 200d, "acOther");
        assertEquals((boolean) res[0], true);
        assertEquals(ac.getFunds() == 800f, true);
        assertEquals(ac2.getFunds() == 200f, true);
    }

    @Test
    public void testTransferOnNotExistingAccount() {
        User user = new User("userName", "pass");
        Account ac = new Account();
        ac.createAccount(user, "acName", true);
        ac.deposit(user, 1000d);
        Object[] res = ac.transfer(user, 200d, "acOther");
        assertEquals((boolean) res[0], false);
        assertEquals(ac.getFunds() == 1000f, true);
    }
}
