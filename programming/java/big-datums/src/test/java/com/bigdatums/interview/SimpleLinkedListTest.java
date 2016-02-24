package com.bigdatums.interview;

import org.junit.Test;
import org.junit.Assert;

public class SimpleLinkedListTest {
    @Test
    public void checkListOrder(){
        //populate list
        SimpleLinkedList ll = new SimpleLinkedList();
        ll.head = new SimpleLinkedList.Node(1);
        ll.head.next = new SimpleLinkedList.Node(3);
        ll.head.next.next = new SimpleLinkedList.Node(5);
        //reverse list
        ll.head = ll.reverse();
        //test
        Assert.assertEquals(ll.head.data, 5);
        Assert.assertEquals(ll.head.next.data, 3);
        Assert.assertEquals(ll.head.next.next.data, 1);
    }
}
