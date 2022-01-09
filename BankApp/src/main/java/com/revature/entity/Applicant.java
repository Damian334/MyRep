package com.revature.entity;

import java.io.Serializable;

public class Applicant implements Serializable  {

    private User user = null;
    private boolean status = false;
    
    public Applicant(User user) {
        this.user = user;
    }
    
    public void accept() {
        status = true;
    }

    public User getUser() {
        return user;
    }

    public boolean getStatus() {
        return status;
    }

    @Override
    public int hashCode() {
        return this.user.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Applicant other = (Applicant) obj;
        if (this.user.equals(other.user)) {
            return true;
        }
        return false;
    }
}
