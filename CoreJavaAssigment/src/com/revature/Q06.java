package com.revature;

public class Q06 {

    public static void execute(int a){
        int a1 = a;
        while(a1 > 1){
            a1-=2;
        }
        if(a1==0){
            System.out.println("the number " +a+" is even");
        }
        if(a1==1){
            System.out.println("the number " +a+" isn't even");
        }
    }
}
