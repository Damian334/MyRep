package com.revature;

import java.util.Scanner;

public class Q17 {

    public static void execute(){
        
        double Principal = findNumber("principal");
        double Rate = findNumber("rate of interest");
        double Time = findNumber("of years");
        double Interest = Principal * Rate * Time;
        
        System.out.println("the Interest for Principal/Rate/Time ("+Principal+","+Rate+","+Time+") is equal to: "+Interest);
    }
    
    
    private static double findNumber(String valueName) {
        Scanner scn = new Scanner(System.in);
        double nr = -1;
        while (true) {
            try {
                System.out.println("enter number of "+valueName+": ");
                nr = Double.valueOf(scn.nextLine().trim());
                break;
            } catch (Exception e) {
                System.out.println("error you need to type a number");
            }
        }
        return nr;
    }
}
