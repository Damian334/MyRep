package com.revature;

import java.util.Date;
import java.util.Scanner;

public class Q14 {

    public static void execute() {

        int nr = findNumber(1,3);
        switch (nr) {
            case 1:
                double nr2 = findNumber();
                double sqrt = Math.sqrt(nr2);
                System.out.println("square root of "+nr2+" is equal to "+sqrt);
                break;
            case 2:
                System.out.println(new Date());
                break;
            case 3:
                System.out.println("I am learning Core Java");
                break;
        }

    }

    private static int findNumber(int min, int max) {
        Scanner scn = new Scanner(System.in);
        int nr = -1;
        while (true) {
            try {
                System.out.println("enter number bettwen " + min + " and " + max + ":");
                nr = Integer.valueOf(scn.nextLine().trim());
                if (nr >= min && nr <= max) {
                    break;
                } else {
                    System.out.println("the number is out of limit");
                }
            } catch (Exception e) {
                System.out.println("error you need to type a number");
            }
        }
        return nr;
    }
    
    private static double findNumber() {
        Scanner scn = new Scanner(System.in);
        double nr = -1;
        while (true) {
            try {
                System.out.println("enter number: ");
                nr = Integer.valueOf(scn.nextLine().trim());
                break;
            } catch (Exception e) {
                System.out.println("error you need to type a number");
            }
        }
        return nr;
    }
}
