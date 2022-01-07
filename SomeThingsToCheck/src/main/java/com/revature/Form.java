package com.revature;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Form {

    
    private static final Logger logger = LogManager.getLogger(Form.class); 
    public static String[] houses = {"Gryffindor", "Hufflepuff", "Ravenclaw", "Slytherin"};
    public static String[] follower = {"Frodo", "Aragorn", "Gandalf", "Legolas"};

    public static String selectHouse() {
        System.out.println("Which Hogwarts House are you?: ");
        return select(houses);
    }

    public static String selectRingFollower() {
        System.out.println("Which member of the Fellowship of the Ring are you?: ");
        return select(follower);
    }

    public static String select(String[] tab) {

        Scanner scn = new Scanner(System.in);
        for (int i = 0; i < tab.length; i++) {
            System.out.println((i + 1) + ". " + tab[i]);
        }
        System.out.println("enter number: ");
        try{
            int nr = Integer.valueOf(scn.nextLine());
            return tab[nr-1];        
        }catch(Exception e){
            //logger
            logger.error("error: "+e);
        }
        return "";        
    }
}
