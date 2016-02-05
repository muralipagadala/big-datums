package com.bigdatums.trees;

import java.util.LinkedList;
import java.util.Queue;

public class BdBst<E extends Comparable<? super E>> {

    private TreeNode<E> root = null;
    private TreeNode<E> left = null;
    private TreeNode<E> right = null;
    private int treeSize = 0;

    public BdBst(){}

    public BdBst(TreeNode node){
        this.root = node;
    }

    public TreeNode<E> getRoot(){
        return root;
    }

    public int getSize() {
        return treeSize;
    }

    //increment tree size
    private void increment(){
        treeSize++;
    }

    //decrement tree size
    private void decrement(){
        treeSize--;
    }

    //recursive insert
    public void insert(E data) {
        root = insert(root, data);
        treeSize++;
        return;
    }

    private TreeNode<E> insert(TreeNode<E> root, E data){
        if(root == null) return new TreeNode<E>(data);

        if(data.compareTo(root.getData()) < 0) root.setLeft(insert(root.getLeft(), data));
        else if (data.compareTo(root.getData()) > 0) root.setRight(insert(root.getRight(), data));

        return root;
    }

    //iterative insert
    public boolean insertIter(E data){
        //insert as root if root is null
        if(root == null) {
            root = new TreeNode<E>(data);
            increment();
            return true;
        }

        TreeNode<E> curr = root;
        int comp;
        while(true) {
            comp = curr.getData().compareTo(data);
            if(comp > 0) {
                TreeNode<E> left = curr.getLeft();
                if(left == null) {
                    TreeNode<E> currLeft = new TreeNode<E>(data);
                    curr.setLeft(currLeft);
                    increment();
                    return true;
                }
                else curr = left;
            }
            else if (comp < 0) {
                TreeNode<E> right = curr.getRight();
                if(right == null) {
                    TreeNode<E> currRight = new TreeNode<E>(data);
                    curr.setRight(currRight);
                    increment();
                    return true;
                }
                else curr = right;
            }
            else return false;
        }
    }

    //recursive find
    public TreeNode<E> find(E data){
        return find(root, data);
    }

    public TreeNode<E> find(TreeNode<E> currentNode, E data){
        if(currentNode == null) return null;
        else if(currentNode.getData().equals(data)) return currentNode;
        else {
            int comp = currentNode.getData().compareTo(data);
            if(comp > 0) return find(currentNode.getLeft(), data);
            else return find(currentNode.getRight(), data);
        }
    }

    //iterative find
    public TreeNode<E> findIter(E data){
        if(root == null) return null;

        TreeNode<E> curr = root;
        int comp;
        while(true) {
            comp = curr.getData().compareTo(data);
            if(comp > 0) {
                TreeNode<E> left = curr.getLeft();
                if(left == null) return null;
                else curr = left;
            }
            else if (comp < 0) {
                TreeNode<E> right = curr.getRight();
                if(right == null) return null;
                else curr = right;
            }
            else return curr;
        }
    }

    //recursive delete
    public void delete(E data) {
        root = delete(root, data);
        decrement();
        return;
    }

    private TreeNode<E> delete(TreeNode<E> root, E data) {
        if(root == null) return root;

        if((data.compareTo(root.getData())) < 0) root.setLeft(delete(root.getLeft(), data));
        else if (data.compareTo(root.getData()) > 0) root.setRight(delete(root.getRight(), data));
        else {
            if(root.getLeft() == null) return root.getRight();
            else if (root.getRight() == null) return root.getLeft();
            else {
                TreeNode<E> smallest = getSmallest(root.getRight());
                root.setData(smallest.getData());
                root.setRight(delete(root.getRight(), smallest.getData()));
            }
        }
        return root;
    }


    //get smallest node in tree
    public TreeNode<E> getSmallest() {
        return getSmallest(root);
    }

    public TreeNode<E> getSmallest(TreeNode<E> node) {
        if(node == null) return null;
        if(node.getLeft() == null) return node;
        else return getSmallest(node.getLeft());
    }

    public void printTree(){
        if(root == null) {
            System.out.println("Empty Tree");
            return;
        }

        StringBuilder mesg = new StringBuilder();
        TreeNode<E> nodeLeft;
        TreeNode<E> nodeRight;

        Queue<TreeNode<E>> queue = new LinkedList<TreeNode<E>>();

        queue.add(root);
        while (!queue.isEmpty()){
            mesg.setLength(0);
            TreeNode<E> node = queue.remove();
            mesg.append("Node:");
            mesg.append(node.getData());

            nodeLeft = node.getLeft();
            nodeRight = node.getRight();
            if(nodeLeft!= null) {
                queue.add(nodeLeft);
                mesg.append(", Left:");
                mesg.append(nodeLeft.getData());
            }
            if(nodeRight != null) {
                queue.add(nodeRight);
                mesg.append(", Right:");
                mesg.append(nodeRight.getData());
            }

            //print node message
            System.out.println(mesg);
        }
    }

    public static void main(String[] args){
        BdBst x = new BdBst();
        x.insert(50);
        x.insert(60);
        x.insert(40);
        x.printTree();
    }

}
