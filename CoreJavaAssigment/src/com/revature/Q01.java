package com.revature;

public class Q01 {

    public static void bubble(int[] tab) {

        for (int i=0;i<tab.length-1;i++) {
            for (int j=0;j<tab.length-i-1;j++) {
                if (tab[j]>tab[j+1]) {
                    int pom = tab[j];
                    tab[j]=tab[j+1];
                    tab[j+1]=pom;
                }
            }
        }
    }
}
