package com.bigdatums.interview;

public class FibonacciRecursive {

    public static int fibonacciRecursive(int n) {
        if(n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else
            return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(fibonacciRecursive(0));
        System.out.println(fibonacciRecursive(1));
        System.out.println(fibonacciRecursive(2));
        System.out.println(fibonacciRecursive(3));
        System.out.println(fibonacciRecursive(4));
        System.out.println(fibonacciRecursive(5));
        System.out.println(fibonacciRecursive(6));
        System.out.println(fibonacciRecursive(7));
        System.out.println(fibonacciRecursive(8));
        System.out.println(fibonacciRecursive(9));
        System.out.println(fibonacciRecursive(10));
    }

}
