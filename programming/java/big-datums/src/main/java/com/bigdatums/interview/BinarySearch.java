package com.bigdatums.interview;


public class BinarySearch {

    public static<E extends Comparable<? super E>> E searchIterative(E[] array, E data) {
        if(data == null || array == null) return null;
        int low = 0;
        int high = array.length -1;

        while(low <= high){
            int mid = low + ((high - low)/2);

            int comp = array[mid].compareTo(data);
            if(comp > 0) high = mid - 1;
            else if(comp < 0) low = mid + 1;
            else return data;
        }
        return null;
    }

    public static<E extends Comparable<? super E>> E searchRecursive(E[] array, E data) {
        return BinarySearch.searchRecursive(array, data, 0, array.length-1);
    }

    public static<E extends Comparable<? super E>> E searchRecursive(E[] array, E data, int low, int high){
        if(data == null || array == null) return null;
        if(high < low) return null;

        int mid = (low + (high - low)/2);
        int comp = array[mid].compareTo(data);

        if(comp > 0) {
            high = mid - 1;
            searchRecursive(array, data, low, high);
        }
        else if (comp < 0) {
            low = mid + 1;
            searchRecursive(array, data, low, high);
        }

        return data;
    }

}
