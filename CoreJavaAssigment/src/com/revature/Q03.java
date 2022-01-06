package com.revature;

public class Q03 {

    public static String execute(String str){
        int lenght = str.length();
        for(int i=0;i<lenght;i++){
            str+=str.charAt(lenght-i-1);
        }
        return str.substring(lenght);
    }
}
