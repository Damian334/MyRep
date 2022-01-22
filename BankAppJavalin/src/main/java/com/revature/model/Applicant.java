package com.revature.model;

import java.io.Serializable;

public class Applicant implements Serializable {

    private int id;
    private int accountId;
    private int userId;
    private boolean status = false;

    
    public Applicant(int accountId, int userId) {
        this.accountId = accountId;
        this.userId = userId;
    }
    
    public Applicant(int id, int accountId, int userId, boolean status) {
        this.id = id;
        this.accountId = accountId;
        this.userId = userId;
        this.status = status;
    }

    public void accept() {
        status = true;
    }

    public int getId() {
        return id;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getUserId() {
        return userId;
    }

    public boolean getStatus() {
        return status;
    }
}
