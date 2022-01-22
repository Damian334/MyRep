package com.revature.model.dao;

import com.revature.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    //name is UNIQUE in db
    public boolean addUser(String name, String password) {
        try {
            Connection c = ConnectionManager.getConnection();
            Statement statement = c.createStatement();
            int rowsAffected = statement.executeUpdate("INSERT INTO users(name,password) VALUES (\'"
                    + name + "\', \'" + password + "\');");
            return true;
        } catch (SQLException e) {
            return false;//this user alredy exist cuz name value is UNIQUE
        }
    }
        
    public User getLogin(String name,String password) {
        try {
            Connection c = ConnectionManager.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM users WHERE name = ? and password= ?");
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                //fench data and make user to return or else return null
                int id = result.getInt("id");
                return new User(id, name, password);
            }
        } catch (SQLException e) {
            System.out.println("UserDao-error: " + e);
        }
        return null;
    }
    
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        try {
            Connection c = ConnectionManager.getConnection();
            PreparedStatement ps = c.prepareStatement("Select * from users;");
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String password = result.getString("password");
                users.add(new User(id, name, password));
            }
        } catch (SQLException e) {
            System.out.println("AccountDao-error: " + e);
            return null;
        }
        return users;
    }
    
    public void clearUsers() {
        try {
            Connection c = ConnectionManager.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM users;");
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("UserDao-error: " + e);
        }
    }

}
