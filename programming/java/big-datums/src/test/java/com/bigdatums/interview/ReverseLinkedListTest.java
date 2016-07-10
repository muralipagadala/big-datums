package com.bigdatums.interview;

import org.junit.Test;
import org.junit.Assert;

public class ReverseLinkedListTest {
    @Test
    public void checkListOrder(){
        //populate list
        ReverseLinkedList ll = new ReverseLinkedList();
        ll.head = new ReverseLinkedList.Node(1);
        ll.head.next = new ReverseLinkedList.Node(3);
        ll.head.next.next = new ReverseLinkedList.Node(5);
        //reverse list
        ll.head = ll.reverse();
        //test
        Assert.assertEquals(ll.head.data, 5);
        Assert.assertEquals(ll.head.next.data, 3);
        Assert.assertEquals(ll.head.next.next.data, 1);
    }
}
