package com.revature.entity;

import com.revature.app.Store;
import java.io.Serializable;

public class Applicant implements Serializable {

    private int id;
    private User user = null;
    private boolean status = false;

    public Applicant(User user) {
        this.user = user;
        id = 1;
        for (Applicant ap : Store.applicantPool) {
            if (ap.id >= id) {
                id = ap.id + 1;
            }
        }
        Store.applicantPool.add(this);
    }

    public void accept() {
        status = true;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
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
