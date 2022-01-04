package com.revature;

public class Q4 {

    public static void execute(int N){
        int factorial = 1;
        for(int i=1;i<=N;i++){
            factorial *= i;
        }
        System.out.println(factorial);
    }
}
