package com.revature.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FundsOperationsDao {

    public boolean addOperation(int accId,String message){
    try {
            Connection c = ConnectionManager.getConnection();
            PreparedStatement ps = c.prepareStatement("INSERT INTO fundsOperations(message,account_id) VALUES(?,?);");
            ps.setString(1, message);
            ps.setInt(2, accId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
        
    public List<String> getOperations(String accountName){
        List<String> info = new ArrayList<>();
        try {
            Connection c = ConnectionManager.getConnection();
            String query = "Select message from fundsOperations o,accounts a where a.id=o.account_id and a.accountName = ?;";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, accountName);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                String message = result.getString("message");
                info.add(message);
            }
        } catch (SQLException e) {
        }
        return info;
    }
        
    public void clearFundsOperations() {
        try {
            Connection c = ConnectionManager.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM fundsOperations;");
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("FundsOperationsDao-error: " + e);
        }
    }
}
