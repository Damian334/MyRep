package com.revature;

public class Q3 {

    public static void execute(String str){
        int lenght = str.length();
        for(int i=0;i<lenght;i++){
            str+=str.charAt(lenght-i-1);
        }
        str=str.substring(lenght);
        System.out.println(str);
    }
}
