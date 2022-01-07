package com.revature;

import com.other.StrOperations;
import com.other.StrOperationsAbstract;

public class Q18 {

    public static void execute(){
        
        StrOperationsAbstract op = new StrOperations();
        
        String s = "find me";
        String s2 = "find Me";
        Boolean b = op.findUppercase(s);
        Boolean b2 = op.findUppercase(s2);
        
        System.out.println("find uppercase for \""+s+"\" the result is: "+b.toString());
        System.out.println("find uppercase for \""+s2+"\" the result is: "+b2.toString());
        
        String res = op.ConvertToUppercase(s2);
        
        System.out.println("convert to uppercase \""+s2+"\" the result is: "+res.toString());
        
        String s3 = "123";
        String s4 = "two";
        
        int nr = op.ConvertToInteger(s3);
        System.out.println("convert to int and increase by 10 \""+s3+"\" the result is: "+nr);
                
        try{
            int nr2 = op.ConvertToInteger(s4);            
        }catch(Exception e){
            System.out.println("convert to int and increase by 10 \""+s4+"\" the result is error: \""+e+"\"");
        }
                
    }
}
