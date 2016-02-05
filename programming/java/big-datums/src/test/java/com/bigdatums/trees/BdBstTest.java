package com.bigdatums.trees;

import org.junit.Test;
import org.junit.Assert;

public class BdBstTest {

    BdBst<Integer> tree;

    public BdBstTest() {
        this.tree = new BdBst<Integer>();
        tree.insert(50);
        tree.insert(75);
        tree.insert(25);
        tree.insert(90);
        tree.insert(60);
        tree.insert(40);
        tree.insert(20);
        tree.insert(10);
        tree.insert(45);
        tree.insert(43);
        tree.insert(44);
    }

    @Test
    public void testFind() {
        int val = tree.find(50).getData();
        Assert.assertEquals(val, 50);

        val = tree.find(10).getData();
        Assert.assertEquals(val, 10);

        Assert.assertNull(tree.find(100));
    }

    @Test
    public void testFindIter() {
        int val = tree.findIter(50).getData();
        Assert.assertEquals(val, 50);

        val = tree.findIter(10).getData();
        Assert.assertEquals(val, 10);

        Assert.assertNull(tree.findIter(100));
    }

    @Test
    public void testSmallest(){
        int val = tree.getSmallest().getData();
        Assert.assertEquals(val, 10);
    }

    @Test
    public void testSize() {
        Assert.assertEquals(tree.getSize(),11);

        tree.delete(10);
        Assert.assertEquals(tree.getSize(),10);

        tree.insertIter(2);
        Assert.assertEquals(tree.getSize(), 11);
    }

    @Test
    public void testDelete() {
        tree.delete(40);
        int val = tree.find(25).getRight().getData();
        Assert.assertEquals(val , 45);

        tree.delete(50);
        val = tree.getRoot().getData();
        Assert.assertEquals(val, 60);

        val = tree.getRoot().getRight().getData();
        Assert.assertEquals(val, 75);

        val = tree.getRoot().getLeft().getData();
        Assert.assertEquals(val, 25);

        tree.delete(20);
        Assert.assertNull(tree.find(20));
    }

    @Test
    public void testInsertIter() {
        Assert.assertFalse(tree.insertIter(75));

        tree.insertIter(100);
        int val = tree.find(90).getRight().getData();
        Assert.assertEquals(val, 100);
    }

}
