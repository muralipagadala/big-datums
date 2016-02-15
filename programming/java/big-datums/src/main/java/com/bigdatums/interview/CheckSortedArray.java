package com.bigdatums.interview;

public class CheckSortedArray {

    public static<E extends Comparable<? super E>> boolean checkSorted(E[] arr){
        E curr = arr[0];
        for(int i=1; i<arr.length; i++){
            if(curr.compareTo(arr[i]) > 0) return false;
            else curr = arr[i];
        }
        return true;
    }

}
