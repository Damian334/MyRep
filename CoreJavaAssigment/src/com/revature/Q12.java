package com.revature;

import java.util.ArrayList;

public class Q12 {

    public static void execute(){
        
        ArrayList<Integer> numbers = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        for(int i=1;i<=100;i++){
            numbers.add(i);
        }
        for(int i:numbers){
            if(i%2==0){
                result.add(i);
            }
        }
        Driver.read(result);        
    }
}
