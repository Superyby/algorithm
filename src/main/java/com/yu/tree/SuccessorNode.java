package com.yu.tree;

public class SuccessorNode {
    public class Node{
        public int value;
        public Node left;
        public Node right;
        public Node parent;
        public Node(int val){
            value = val;
        }
    }
    public static Node getSuccessorNode(Node node){
        if (node == null){
            return node;
        }
        if (node.right != null){
            return getLeftMost(node.right);
        }else {
            Node parent = node.parent;
            while (parent != null && parent.left != node){
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }
    public static Node getLeftMost(Node node){
        if (node == null){
            return node;
        }
        while (node.left != null){
            node = node.left;
        }
        return node;
    }
}
