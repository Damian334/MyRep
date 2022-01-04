package com.revature;

public class Q5 {

    public static void execute(String str,int idx){
        String sub = "";
        if(idx>str.length()){
            System.out.println("idx is too large number");
        }
        for(int i=0;i<idx;i++){
            sub+=str.charAt(i);
        }
        System.out.println(sub);
        
    }
}
