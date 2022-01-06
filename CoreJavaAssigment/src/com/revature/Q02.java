package com.revature;

public class Q02 {

    public static int[] execute(int nr){
        int[] fib = new int[nr];
        
        if(nr>=1)
            fib[0]=0;
        if(nr>=2)
            fib[1]=1;
        if(nr>2)
        for(int i=2;i<nr;i++){
            fib[i]=fib[i-1]+fib[i-2];
        }
        return fib;
    }
}
