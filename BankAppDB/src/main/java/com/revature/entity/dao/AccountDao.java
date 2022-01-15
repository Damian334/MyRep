package com.revature.entity.dao;

import com.revature.entity.Account;
import com.revature.entity.Applicant;
import com.revature.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {

    public boolean addAccount(String accountName, User user) {
        try {
            Connection c = ConnectionManager.getConnection();
            Statement statement = c.createStatement();
            statement.executeUpdate("INSERT INTO accounts(accountName,funds) VALUES (\'" + accountName + "\',0.0);");
            //fundsOperations are empty at begin then i dont need to add
            //at begin i need to add 1 user to applicants who make a account            
            int accountId = getIdByName(accountName);
            Applicant applicant = new Applicant(accountId, user.getId());
            ApplicantDao app = new ApplicantDao();
            app.addApplicant(applicant, accountName);
            return true;
        } catch (SQLException e) {
            return false;//this account alredy exist cuz accountName value is UNIQUE
        }
    }

    public boolean isAccountNameTaken(String accountName) {
        try {
            Connection c = ConnectionManager.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM accounts WHERE accountName = ?");
            ps.setString(1, accountName);
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public int getIdByName(String accountName) {
        try {
            Connection c = ConnectionManager.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT id FROM accounts WHERE accountName = ?");
            ps.setString(1, accountName);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                return result.getInt("id");
            }
        } catch (SQLException e) {
        }
        return -1;
    }

    public Account getAccount(String accountName) {
        return getAccount(accountName, false);
    }

    public Account getAccount(String accountName, boolean status) {
        try {
            Connection c = ConnectionManager.getConnection();
            String query = "SELECT a.* FROM accounts a,applicants WHERE accountName = ?";
            query += status ? " and status=true":"";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, accountName);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                int id = result.getInt("id");
                double funds = result.getDouble("funds");
                return new Account(id, funds, accountName);
            }
        } catch (SQLException e) {
            System.out.println("AccountDao-error: " + e);
        }
        return null;
    }

    public List<Account> getAccounts(String userName) {
        List<Account> accounts = new ArrayList<>();
        try {
            Connection c = ConnectionManager.getConnection();
            String query = "Select a.* from users u,accounts a,applicants ap where u.id=ap.user_id and a.id=ap.account_id "
                    + "and u.name = ?;";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, userName);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String accountName = result.getString("accountName");
                double funds = result.getDouble("funds");
                accounts.add(new Account(id, funds, accountName));
            }
        } catch (SQLException e) {
        }
        return accounts;
    }
    
    public List<Account> getAllAccounts(){
        List<Account> accounts = new ArrayList<>();
        try {
            Connection c = ConnectionManager.getConnection();
            PreparedStatement ps = c.prepareStatement("Select * from accounts;");
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String accountName = result.getString("accountName");
                double funds = result.getDouble("funds");
                accounts.add(new Account(id, funds, accountName));
            }
        } catch (SQLException e) {
        }
        return accounts;
    }
        
    public Double getFunds(String accountName){        
        try {
            Connection c = ConnectionManager.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT funds from accounts where accountName= ?;");
            ps.setString(1, accountName);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                return result.getDouble("funds");
            }
        } catch (SQLException e) {
        }
        return null;
    }
    
    public void setFunds(String accountName,double funds) {
        try {
            Connection c = ConnectionManager.getConnection();
            String query = "update accounts set funds= ? where accountName= ?;";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setDouble(1, funds);
            ps.setString(2, accountName);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    public int deleteAccount(String accountName) {
        try {
            Connection c = ConnectionManager.getConnection();
            ApplicantDao app = new ApplicantDao();
            app.deleteApplicants(accountName);//first remove all connections
            String query = "delete from accounts where accountName= ? ;";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, accountName);
            return ps.executeUpdate();            
        } catch (SQLException e) {
        }
        return 0;
    }

    public void clearAccounts() {
        try {
            Connection c = ConnectionManager.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM accounts;");
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("AccountDao-error: " + e);
        }
    }
}
