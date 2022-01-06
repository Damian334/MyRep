package com.revature;

public class Q13 {

    public static void execute(int rows){
        for(int i=1;i<=rows;i++){
            String str="";
            for(int j=0;j<i;j++){
                if(i%2==1){
                    str += (j%2==0) ? "0 " : "1 ";                    
                }else{
                    str += (j%2==0) ? "1 " : "0 "; 
                }
            }
            System.out.println(str);
        }
    }
}
