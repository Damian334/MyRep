package com.revature.model.dao;

import com.revature.model.Applicant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicantDao {

    public boolean addApplicant(Applicant applicant, String accountName) {
        try {
            Connection c = ConnectionManager.getConnection();
            PreparedStatement ps = c.prepareStatement("INSERT INTO applicants(status,account_id,user_id) VALUES(?,?,?);");
            ps.setBoolean(1, applicant.getStatus());
            ps.setInt(2, applicant.getAccountId());
            ps.setInt(3, applicant.getUserId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    public List<String[]> getApplicantsInfo() {
        List<String[]> info = new ArrayList<>();
        try {
            Connection c = ConnectionManager.getConnection();
            String query = "Select ap.id,u.name,a.accountName,a.funds from users u,accounts a,applicants ap where "
                    + "u.id=ap.user_id and a.id=ap.account_id and ap.status = false;";
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                Integer applicant_id = result.getInt("id");
                String userName = result.getString("name");
                String accountName = result.getString("accountName");
                Double funds = result.getDouble("funds");
                String[] tab = {applicant_id.toString(), userName, accountName, funds.toString()};
                info.add(tab);
            }
        } catch (SQLException e) {
        }
        return info;
    }

    public boolean acceptById(int id) {
        try {
            Connection c = ConnectionManager.getConnection();
            String query = "update applicants set status=true where id= ? ;";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, id);//can give result if i get done
            int res = ps.executeUpdate();
            return res > 0 ? true : false;
        } catch (SQLException e) {
        }
        return false;
    }

    public List<Integer> getAllId() {
        List<Integer> list = new ArrayList<>();
        try {
            Connection c = ConnectionManager.getConnection();
            String query = "select id from applicants";
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                list.add(result.getInt("id"));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public void deleteApplicants(String accountName) {
        try {
            Connection c = ConnectionManager.getConnection();
            String query = "delete from applicants where account_id in (select id from accounts where accountName= ? );";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, accountName);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void clearApplicants() {
        try {
            Connection c = ConnectionManager.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM applicants;");
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ApplicantDao-error: " + e);
        }
    }
}
