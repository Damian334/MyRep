package com.revature;

import java.util.ArrayList;

public class Q09 {

    public static void execute() {
        ArrayList<Integer> numbers = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i <= 100; ++i) {
            numbers.add(i);
            if (isPrime(i)) {
                result.add(i);
            }
        } 
        Driver.read(result);
    }

    private static boolean isPrime(int nr) {
        if (nr == 0 || nr == 1) {
            return false;
        }
        for (int i = 2; i < nr; ++i) {
            if (nr % i == 0) {
                return false;
            }
        }
        return true;
    }
}
