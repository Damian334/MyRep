package com.revature;

public class Q1 {
    
    public static void execute(){
        int[] tab = {1,0,5,6,3,2,3,7,9,8,4};
        bubble(tab);
        for(int i:tab){
            System.out.print(i);
        }
        System.out.println();
    }

    private static void bubble(int[] tab) {

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
