package com.revature.datatype;

public class DataTypes {

    public void dataTypes() {

        // Representing WHOLE numbers
        int i = 0; // Variable initialization: variables name and type are established, and it has an explicit value. 32 bits in length.
        short s; // Short is 16 bits. This is called a variable declaration: name and type are established, but no value is assigned.
        long l; // Long is 64 bits.

        // Representing (approximating) fractions or real numbers
        float f = 3.9f; // 32 bits
        double d = 3.9; // 64 bits

        // Letters
        char c = 'c'; // 16 bits

        // True and false
        boolean b = true; // 1 bit*, only need 0 or 1

        // The basic unit
        byte bt; // 8 bits, can specify bits in a bit string
    }

    public int add(int a, int b) {
        return a + b;
    }

    public void printDogName(Dog d){
        System.out.println("dog name "+d.name);
    }
}
