package com.revature;

import com.other.PomQ11;

public class Q11 {
    
    public static void execute(){
        PomQ11 pom = new PomQ11();
        
        System.out.println("before: "+pom.toString());
        
        pom.x = 3;
        pom.y = 5;
        
        System.out.println("after:  "+pom.toString());
    }
}
