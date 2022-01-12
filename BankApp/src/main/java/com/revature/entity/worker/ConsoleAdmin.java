package com.revature.entity.worker;

import com.revature.entity.Account;
import com.revature.entity.User;
import java.util.Scanner;
import java.util.Set;

public class ConsoleAdmin extends Admin {

    public ConsoleAdmin(Set<User> usersPool, Set<Account> accountsPool) {
        super(usersPool, accountsPool);
    }

    public ConsoleAdmin() {
        super();
    }

    public boolean approveAccounts() {
        Scanner scn = new Scanner(System.in);
        String msg = approveShowAccounts();
        System.out.println(msg);
        try {
            String[] s = scn.nextLine().trim().split(" ");
            Integer nr = Integer.valueOf(s[0]);
            String str = s[1];
            return approveAccounts(str,nr);
        } catch (Exception e) {
            System.out.println("error: " + e);
            super.getLogger().error("Emplye approveAccounts error: "+e);
        }
        return false;
    }

}
