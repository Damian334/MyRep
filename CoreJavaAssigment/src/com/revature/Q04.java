package com.revature;

public class Q04 {

    public static int factorial(int N){
        int nr = 1;
        for(int i=1;i<=N;i++){
            nr *= i;
        }
        return nr;
    }
}
