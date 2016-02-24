package com.bigdatums.interview;

public class SimpleLinkedList {

    public static Node head;

    public static class Node {
        public int data;
        public Node next = null;

        public Node(int data) {
            this.data = data;
        }
    }

    public Node reverse(){
        if(head.next == null) return head;
        Node curr = head;
        Node prev = null;
        Node next = null;

        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public void printList(){
        Node curr = head;
        while(curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }

    public static void main(String[] args){
        SimpleLinkedList ll = new SimpleLinkedList();
        ll.head = new Node(1);
        ll.head.next = new Node(2);
        ll.head.next.next = new Node(3);
        ll.head = ll.reverse();
        ll.printList();
    }

}
