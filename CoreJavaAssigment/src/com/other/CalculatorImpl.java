package com.other;

public class CalculatorImpl implements Calculator{

    @Override
    public double addition(double a, double b) {
        return a + b;
    }

    @Override
    public double subtraction(double a, double b) {
        return a - b;
    }

    @Override
    public double multiplication(double a, double b) {
        return a * b;
    }

    @Override
    public double division(double a, double b) {
        if(b==0f){
            throw new ArithmeticException();
        }
        return a / b;
    }
}
