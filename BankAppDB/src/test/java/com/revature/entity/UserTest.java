package com.revature.entity;

import com.revature.entity.dao.AccountDao;
import com.revature.entity.dao.ApplicantDao;
import com.revature.entity.dao.ConnectionManager;
import com.revature.entity.dao.FundsOperationsDao;
import com.revature.entity.dao.UserDao;
import com.revature.entity.worker.Employe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

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
    public void testRegistration() {
        User user = new User();
        Object[] ob = user.registration("name", "pass");
        boolean result = (boolean) ob[0];
        assertEquals(result, true);
    }

    @Test
    public void testRegistrationFailed() {
        User user = new User();
        //usersPool.add(new User("name", "pass"));
        Object[] ob = user.registration("name", "pass");
        Object[] ob2 = user.registration("name", "pass");//2nd ty of adding the same user
        boolean result = (boolean) ob2[0];
        assertEquals(result, false);
    }

    @Test
    public void testLogin() {
        User user = new User();
        user.registration("name", "pass");
        user.registration("name2", "pass2");
        User result = User.login("name2", "pass2");
        assertEquals(result.getName(), "name2");
    }

    @Test
    public void testLoginFailed() {
        User user = new User();
        user.registration("name", "pass");
        user.registration("name2", "pass2");
        User result = User.login("alien", "pass2");
        assertEquals(result, null);
    }

    @Test
    public void testGetUserAccounts() {
        User user = new User();
        user.registration("name", "pass");
        Account ac = new Account();
        User userLoged = User.login("name", "pass");
        ac.createAccount(userLoged, "acName");
        ac.createAccount(userLoged, "acName2");
        assertEquals(accDao.getAccounts("name").size(), 2);
    }

    @Test
    public void testGetUserAccountsFailed() {
        User user = new User();
        user.registration("name", "pass");
        Account ac = new Account();
        User userLoged = User.login("name", "pass");
        ac.createAccount(userLoged, "acName");// create account for user  
        ac.createAccount(userLoged, "acName");// create 2nd same account for user 
        assertEquals(accDao.getAccounts("name").size(), 1);//the user will create only 1 account
    }

    @Test
    public void testGetUserConfirmedAccounts() {
        User user = new User();
        user.registration("name", "pass");
        Account ac = new Account();
        User userLoged = User.login("name", "pass");
        ac.createAccount(userLoged, "acName");
        Employe e = new Employe();
        int id = appDao.getAllId().get(0);
        assertEquals(e.approveAccounts("t", id), true);
        assertEquals(accDao.getAccounts("name").size(), 1);
    }

    @Test
    public void testGetUserConfirmedAccount() {
        User user = new User();
        user.registration("name", "pass");
        Account ac = new Account();
        User userLoged = User.login("name", "pass");
        ac.createAccount(userLoged, "acName");
        Employe e = new Employe();
        int id = appDao.getAllId().get(0);
        assertEquals(e.approveAccounts("t", id), true);
        assertEquals(user.getUserConfirmedAccount("acName").getAccountName(), "acName");
    }

    @Test
    public void testGetUserConfirmedAccountFailed() {
        User user = new User();
        user.registration("name", "pass");
        Account ac = new Account();
        User userLoged = User.login("name", "pass");
        ac.createAccount(userLoged, "acName");
        //account wasn't confirment by employe or admin
        assertEquals(user.getUserConfirmedAccount("acName"), null);
    }
}
