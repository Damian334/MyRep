package com.revature.datatype;

public class Driver {
    
    public static void main(String[] args) {
        
        DataTypes dt = new DataTypes();
        System.out.println(dt.add(1,2));
        
        int answer = dt.add(1, 2);
        System.out.println(answer);
        
        Dog felix = new Dog();
        felix.name = "felix";
        felix.age=2;
        felix.weight = 106.5;
        felix.breed = "German Separd";
    }
}
