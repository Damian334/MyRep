package com.revature.entity.worker;

import com.revature.model.Account;
import com.revature.model.User;
import com.revature.model.dao.AccountDao;
import com.revature.model.dao.ApplicantDao;
import com.revature.model.dao.ConnectionManager;
import com.revature.model.dao.FundsOperationsDao;
import com.revature.model.dao.UserDao;
import com.revature.model.worker.Employe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeTest {

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
    public void testApproveAccounts() {
        User user = new User();
        user.registration("name", "pass");
        Account ac = new Account();
        User userLoged = User.login("name", "pass");
        ac.createAccount(userLoged, "acName");// create account for user
        Employe emp = new Employe();
        assertEquals(emp.approveAccounts("t", appDao.getAllId().get(0)), true);
    }

    @Test
    public void testApproveAccountsFailed() {
        User user = new User();
        user.registration("name", "pass");
        Account ac = new Account();
        User userLoged = User.login("name", "pass");
        ac.createAccount(userLoged, "acName");// create account for user
        Employe emp = new Employe();//e.g select wrong index when there is only 1 record
        assertEquals(emp.approveAccounts("t", appDao.getAllId().get(0) + 5), false);
    }
}
