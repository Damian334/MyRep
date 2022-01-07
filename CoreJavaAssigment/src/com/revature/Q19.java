package com.revature;

import java.util.ArrayList;

public class Q19 {

    public static void execute() {

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        Driver.read(list);

        int size = list.size();

        for (int i = 0; i < size; i++) {
            if (list.get(i) % 2 == 0) {
                list.add(list.get(i));
            }
        }
        Driver.read(list);

        for(int i=list.size()-1;i>=0;i--){
            if(isPrime(list.get(i))){
                list.remove(i);
            }
        }
        Driver.read(list);        
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
