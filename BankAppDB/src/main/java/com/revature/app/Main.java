package com.revature.app;

import com.revature.entity.User;
import com.revature.entity.dao.AccountDao;
import com.revature.entity.dao.ConnectionManager;
import com.revature.entity.dao.UserDao;
import java.sql.Connection;

public class Main {   
    
    public static void main(String[] args) {
        Bank b = new Bank();
        b.run();
        
        //UserDao us = new UserDao();
        //System.out.println("INSERT INTO users(name,password) VALUES (\'" + "any" + "\', \'" + "pass" + "\')");
        //us.addUser("name", "pass");
        //System.out.println(us.getUser("name"));
        //User user = us.getUser("name");
        
        //System.out.println(user);
        //AccountDao acc = new AccountDao();
        //acc.addAccount("new acc", user);
        
    }
}
