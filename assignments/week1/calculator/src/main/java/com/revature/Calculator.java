package com.revature;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int exp(int n, int power) {
        return (int) Math.pow(n, power);
    }

    public double div(double a, double b) {
        return a / b;
    }

    public int mul(int a, int b) {
        return a * b;
    }
}
