package com.bigdatums.trees;

public class TreeNode<E extends Comparable<? super E>> {
    private E data;
    private TreeNode<E> left = null;
    private TreeNode<E> right = null;

    public TreeNode(E e) {
        data = e;
    }

    public E getData(){
        return data;
    }

    public void setData(E e){
        data = e;
    }

    public TreeNode<E> getLeft(){
        return left;
    }

    public TreeNode<E> getRight(){
        return right;
    }

    public void setRight(TreeNode node){
         right = node;
    }

    public void setLeft(TreeNode node){
        left = node;
    }

    public boolean isLeaf(){
        if(left == null && right == null) return true;
        else return false;
    }

    public boolean hasBothChildren(){
        if (left != null && right != null) return true;
        else return false;
    }

    public String toString(){
        return data.toString();
    }
}
