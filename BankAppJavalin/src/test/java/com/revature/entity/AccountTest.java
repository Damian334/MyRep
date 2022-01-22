package com.revature.entity;

import com.revature.model.dao.AccountDao;
import com.revature.model.dao.ApplicantDao;
import com.revature.model.dao.ConnectionManager;
import com.revature.model.dao.FundsOperationsDao;
import com.revature.model.dao.UserDao;
import com.revature.model.Account;
import com.revature.model.User;
import com.revature.model.worker.Employe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {

    private static String connectionString = "jdbc:postgresql://ella.db.elephantsql.com:5432/nzjtpvyl";
    private static String username = "nzjtpvyl";
    private static String password = "PKsXPvcPP2DANZ99B8ojTjLS44at-QR1";
    private UserDao userDao = new UserDao();
    private AccountDao accDao = new AccountDao();
    private ApplicantDao appDao = new ApplicantDao();
    private FundsOperationsDao operationDao = new FundsOperationsDao();

    @BeforeAll
    public static void setupAllTests() {
        ConnectionManager.setConnection(connectionString, username, password);
    }

    @BeforeEach
    public void setupEachTest() {
        userDao.clearUsers();
        accDao.clearAccounts();
        appDao.clearApplicants();
        operationDao.clearFundsOperations();
    }

    @Test
    public void testCreateAccount() {
        User user = new User();
        user.registration("name", "pass");
        Account ac = new Account();
        User userLoged = User.login("name", "pass");
        Object[] res = ac.createAccount(userLoged, "acName");
        assertEquals((boolean) res[0], true);
    }

    @Test
    public void testCreateAccountFailed() {
        User user = new User();
        user.registration("name", "pass");
        Account ac = new Account();
        User userLoged = User.login("name", "pass");
        ac.createAccount(userLoged, "acName");//first i am creating an account ,and later tring to do it again
        Object[] res = ac.createAccount(userLoged, "acName");
        assertEquals((boolean) res[0], false);
    }

    @Test
    public void testAddApplicant() {
        User user = new User();
        user.registration("name", "pass");
        Account ac = new Account();
        User userLoged = User.login("name", "pass");
        ac.createAccount(userLoged, "acName");//first i am creating an account ,and later tring to do it again
        user.registration("name2", "pass2");
        User userLoged2 = User.login("name2", "pass2");
        Object[] res = ac.addApplicant(userLoged2, "acName");
        assertEquals((boolean) res[0], true);//add other customer to account
        assertEquals(appDao.getAllId().size(), 2);//and account get 2 potential customers        
    }

    @Test
    public void testAddApplicantFailed() {
        User user = new User();
        user.registration("name", "pass");
        Account ac = new Account();
        User userLoged = User.login("name", "pass");
        ac.createAccount(userLoged, "acName");
        Object[] res = ac.addApplicant(userLoged, "acName");
        assertEquals((boolean) res[0], false);
    }

    @Test
    public void testDeposit() {
        User user = new User();
        user.registration("name", "pass");
        Account ac = new Account();
        User userLoged = User.login("name", "pass");
        ac.createAccount(userLoged, "acName");
        Employe emp = new Employe();
        emp.approveAccounts("t", appDao.getAllId().get(0));
        Account confirmedAccount = userLoged.getUserConfirmedAccount("acName");
        confirmedAccount.deposit(userLoged, 1000d);
        assertEquals(accDao.getFunds("acName") == 1000d, true);
    }

    @Test
    public void testDepositFailed() {
        User user = new User();
        user.registration("name", "pass");
        Account ac = new Account();
        User userLoged = User.login("name", "pass");
        ac.createAccount(userLoged, "acName");
        Employe emp = new Employe();
        emp.approveAccounts("t", appDao.getAllId().get(0));
        Account confirmedAccount = userLoged.getUserConfirmedAccount("acName");
        confirmedAccount.deposit(userLoged, -1000d);
        assertEquals(accDao.getFunds("acName") == 0d, true);
    }

    @Test
    public void testWithdraw() {
        User user = new User();
        user.registration("name", "pass");
        Account ac = new Account();
        User userLoged = User.login("name", "pass");
        ac.createAccount(userLoged, "acName");
        Employe emp = new Employe();
        emp.approveAccounts("t", appDao.getAllId().get(0));
        Account confirmedAccount = userLoged.getUserConfirmedAccount("acName");
        confirmedAccount.deposit(userLoged, 1000d);
        Object[] res = confirmedAccount.withdraw(userLoged, 500d);
        assertEquals((boolean) res[0], true);
        assertEquals(accDao.getFunds("acName") == 1000f - 500d, true);
    }

    @Test
    public void testWithdrawFail() {
        User user = new User();
        user.registration("name", "pass");
        Account ac = new Account();
        User userLoged = User.login("name", "pass");
        ac.createAccount(userLoged, "acName");
        Employe emp = new Employe();
        emp.approveAccounts("t", appDao.getAllId().get(0));
        Account confirmedAccount = userLoged.getUserConfirmedAccount("acName");
        confirmedAccount.deposit(userLoged, 1000d);
        Object[] res = confirmedAccount.withdraw(userLoged, 2000d);
        assertEquals((boolean) res[0], false);
    }

    @Test
    public void testTransfer() {
        User user = new User();
        user.registration("name", "pass");
        Account ac = new Account();
        User userLoged = User.login("name", "pass");
        ac.createAccount(userLoged, "acName");
        ac.createAccount(userLoged, "acOther");
        Employe emp = new Employe();
        emp.approveAccounts("t", appDao.getAllId().get(0));
        Account confirmedAccount = userLoged.getUserConfirmedAccount("acName");
        confirmedAccount.deposit(userLoged, 1000d);
        Object[] res = confirmedAccount.transfer(userLoged, 200d, "acOther");
        assertEquals((boolean) res[0], true);
        assertEquals(accDao.getFunds("acName") == 800d, true);
        assertEquals(accDao.getFunds("acOther") == 200d, true);
    }

    @Test
    public void testTransferOnNotExistingAccount() {
        User user = new User();
        user.registration("name", "pass");
        Account ac = new Account();
        User userLoged = User.login("name", "pass");
        ac.createAccount(userLoged, "acName");
        Employe emp = new Employe();
        emp.approveAccounts("t", appDao.getAllId().get(0));
        Account confirmedAccount = userLoged.getUserConfirmedAccount("acName");
        confirmedAccount.deposit(userLoged, 1000d);
        Object[] res = confirmedAccount.transfer(userLoged, 200d, "acOther");
        assertEquals((boolean) res[0], false);
    }
}
