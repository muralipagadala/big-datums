package com.bigdatums.interview;

import org.junit.Test;
import org.junit.Assert;

public class CheckSortedArrayTest {
    String[] sorted = {"a", "b", "c", "x", "y", "z"};
    String[] unsorted = {"a", "b", "x", "c", "y", "z"};

    @Test
    public void checkSorted(){
        Assert.assertTrue(CheckSortedArray.checkSorted(sorted));
    }

    @Test
    public void checkUnsorted(){
        Assert.assertFalse(CheckSortedArray.checkSorted(unsorted));
    }
}
