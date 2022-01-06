package com.revature;

public class Q05 {

    public static String substring(String str,int idx){
        String sub = "";
        if(idx>str.length()){
            System.out.println("idx is too large number");
        }
        for(int i=0;i<idx;i++){
            sub+=str.charAt(i);
        }
        return sub;
    }
}
