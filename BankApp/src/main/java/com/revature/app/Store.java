package com.revature.app;

import com.revature.entity.Account;
import com.revature.entity.User;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Store implements Serializable {

    public Set<User> users = new HashSet<>();
    public Set<Account> accounts = new HashSet<>();

    public Store() {
    }

    public Store(Set<User> users, Set<Account> accounts) {
        this.users = users;
        this.accounts = accounts;
    }
}
