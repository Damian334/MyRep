package com.revature.entity.console;

import com.revature.entity.worker.Admin;
import java.util.Scanner;

public class ConsoleAdmin extends Admin {

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
            return approveAccounts(str, nr);
        } catch (Exception e) {
            super.getLogger().error("Emplye approveAccounts error: " + e);
        }
        return false;
    }

    public boolean removeAccountByName(String accountName) {
        boolean res = super.removeAccountByName(accountName);
        if (res) {
            System.out.println("account " + accountName + " was removed");
        }
        return res;
    }
}
