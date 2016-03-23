package com.bigdatums.interview;

public class SumValuesInArray {
    public static <T extends Number> double sumArray(T[] arr) {
        double sum = 0;
        for(int i=0; i < arr.length; i++) {
            sum = sum + arr[i].doubleValue();
        }
        return sum;
    }

    public static int sumArray(int[] arr) {
        int sum = 0;
        for(int i=0; i < arr.length; i++) {
            sum = sum + arr[i];
        }
        return sum;
    }

    public static double sumArray(double[] arr) {
        double sum = 0;
        for(int i=0; i < arr.length; i++) {
            sum = sum + arr[i];
        }
        return sum;
    }

    public static void main(String[] args){
        Number[] nums = {1, 2, 3, 4};
        double sum = sumArray(nums);
        System.out.println(sum);
    }
}
