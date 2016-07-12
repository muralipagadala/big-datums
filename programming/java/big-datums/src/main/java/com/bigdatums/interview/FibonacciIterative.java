package com.bigdatums.interview;

public class FibonacciIterative {

    public static int fibonacciIterative(int n) {
        int num1 = 0;
        int num2 = 1;
        int output = 0;

        if(n == 0)
            return 0;
        if(n == 1)
            return 1;

        for(int i=1; i<n; i++) {
            output = num1 + num2;
            num1 = num2;
            num2 = output;
        }
        return output;
    }

    public static void main(String[] args) {
        System.out.println(fibonacciIterative(0));
        System.out.println(fibonacciIterative(1));
        System.out.println(fibonacciIterative(2));
        System.out.println(fibonacciIterative(3));
        System.out.println(fibonacciIterative(4));
        System.out.println(fibonacciIterative(5));
        System.out.println(fibonacciIterative(6));
        System.out.println(fibonacciIterative(7));
        System.out.println(fibonacciIterative(8));
        System.out.println(fibonacciIterative(9));
        System.out.println(fibonacciIterative(10));
    }

}
