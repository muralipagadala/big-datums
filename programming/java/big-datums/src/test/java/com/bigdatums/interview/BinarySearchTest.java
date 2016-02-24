package com.bigdatums.interview;

import org.junit.Test;
import org.junit.Assert;

public class BinarySearchTest {

    String[] arr = {"a", "b", "d", "e", "i", "x", "y", "z"};

    @Test
    public void searchIterativeTest(){
        Assert.assertEquals(BinarySearch.searchIterative(arr, "a"), "a");
        Assert.assertEquals(BinarySearch.searchIterative(arr, "e"), "e");
        Assert.assertEquals(BinarySearch.searchIterative(arr, "z"), "z");
        Assert.assertEquals(BinarySearch.searchIterative(arr, "h"), null);
        Assert.assertEquals(BinarySearch.searchIterative(arr, null), null);
    }

    @Test
    public void searchRecursiveTest(){
        Assert.assertEquals(BinarySearch.searchRecursive(arr, "a"), "a");
        Assert.assertEquals(BinarySearch.searchRecursive(arr, "e"), "e");
        Assert.assertEquals(BinarySearch.searchRecursive(arr, "z"), "z");
        Assert.assertEquals(BinarySearch.searchIterative(arr, "h"), null);
        Assert.assertEquals(BinarySearch.searchRecursive(arr, null), null);
    }

}
