package com.revature;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Q20 {

    public static void execute() {

        String fileName = "Data.txt";
        String dir = System.getProperty("user.dir") + "\\" + fileName;
        try {
            File file = new File(dir);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(":");
                String str = "";                
                str += "Name: " + data[0] + " " + data[1]+"\n";
                str += "Age: " + data[2] + " years\n";
                str += "State: " + data[3] + " State\n";                
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            System.out.println("error: " + e);
        }
    }
}
