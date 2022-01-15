package com.revature.app;

import com.revature.entity.Account;
import com.revature.entity.Applicant;
import com.revature.entity.User;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//not used because of db
public class Store implements Serializable {

    public static Set<User> usersPool = new HashSet<>();
    public static Set<Account> accountsPool = new HashSet<>();
    public static List<Applicant> applicantPool = new ArrayList<>();//only to check its id

    public void save() {
        StoreSave store = new StoreSave(usersPool, accountsPool, applicantPool);
        String fileName = "data.txt";
        String dir = System.getProperty("user.dir") + "\\" + fileName;

        FileOutputStream fileOutput;
        try {
            fileOutput = new FileOutputStream(dir);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
            outputStream.writeObject(store);
            fileOutput.close();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("save error: " + e);
        }
    }

    public void read() {
        String fileName = "data.txt";
        String dir = System.getProperty("user.dir") + "\\" + fileName;
        try {
            FileInputStream fiStream = new FileInputStream(dir);
            ObjectInputStream objectStream = new ObjectInputStream(fiStream);
            StoreSave store = (StoreSave) objectStream.readObject();
            fiStream.close();
            objectStream.close();
            //now i am loading class accounts and users
            usersPool = store.usersPool;
            accountsPool = store.accountsPool;
            applicantPool = store.applicantPool;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("read error: " + e);
        }
    }

    private class StoreSave implements Serializable {

        public Set<User> usersPool = new HashSet<>();
        public Set<Account> accountsPool = new HashSet<>();
        public List<Applicant> applicantPool = new ArrayList<>();

        public StoreSave(Set<User> usersPool, Set<Account> accountsPool, List<Applicant> applicantPool) {
            this.usersPool = usersPool;
            this.accountsPool = accountsPool;
            this.applicantPool = applicantPool;
        }
    }
}
