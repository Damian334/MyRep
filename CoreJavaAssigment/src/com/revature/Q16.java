package com.revature;

public class Q16 {

    public static void main(String[] args) {
        
        System.out.println("you writed:");
        for(String s:args){
            System.out.println("\""+s+"\" word have: "+s.length()+" characters");
        }
    }
}
