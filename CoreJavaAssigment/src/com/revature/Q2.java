package com.revature;

public class Q2 {

    public static void execute(int nr){
        int[] fib = new int[nr];
        
        if(nr>=1)
            fib[0]=0;
        if(nr>=2)
            fib[1]=1;
        if(nr>2)
        for(int i=2;i<nr;i++){
            fib[i]=fib[i-1]+fib[i-2];
        }
        
        for(int i:fib)
            System.out.print(i+",");
        System.out.println();
    }
}
