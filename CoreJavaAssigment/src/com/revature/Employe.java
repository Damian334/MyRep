package com.revature;

import java.util.Comparator;

public class Employe implements Comparator<Employe> {

    public String name;
    public String department;
    public Integer age;
    
    public Employe(){        
    }
    
    public Employe(String name, String department, Integer age) {
        this.name = name;
        this.department = department;
        this.age = age;
    }

    @Override
    public String toString() {
        return "(" + name + "," + department + "," + age + ")";
    }

    @Override
    public int compare(Employe e1, Employe e2) {
        if (e1.name == e2.name) {
            if (e1.department == e2.department) {
                return e1.age.compareTo(e2.age);
            } else {
                return e1.department.compareTo(e2.department);
            }
        } else {
            return e1.name.compareTo(e2.name);
        }
    }

}
