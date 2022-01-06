package com.revature;

import com.other.Calculator;
import com.other.CalculatorImpl;

public class Q15 {

    public static void execute() {
        System.out.println("testing implemetation od Calculator:");
        
        Calculator c = new CalculatorImpl();
        double a = 5, b = 2;
        double r1, r2, r3, r4;
        r1 = c.addition(a, b);
        System.out.println("addition of " + a + " and " + b + " = " + r1);
        r2 = c.subtraction(a, b);
        System.out.println("subtraction of " + a + " and " + b + " = " + r2);
        r3 = c.multiplication(a, b);
        System.out.println("multiplication of " + a + " and " + b + " = " + r3);
        r4 = c.division(a, b);
        System.out.println("division of " + a + " and " + b + " = " + r4);
        try {
            b = 0;
            r4 = c.division(a, b);
            System.out.println("division of " + a + " and " + b + " = " + r4);
        } catch (ArithmeticException e) {
            System.out.println("error when dividing by 0: " + e);
        }
    }
}
