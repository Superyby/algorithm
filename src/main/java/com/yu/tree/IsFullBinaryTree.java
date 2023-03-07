package com.yu.tree;

/**
 * 满二叉树  最大深度 l   ; 节点数   N   满二叉树：N = 2^l - 1;
 */
public class IsFullBinaryTree {
    class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isFull(Node head) {
        if (head == null) {
            return true;
        }
        Info data = full(head); //整棵树的两个信息收上来了
        return data.nodes == (1 << data.height - 1);
    }

    public static class Info {
        public int height;
        public int nodes;

        public Info(int h, int n) {
            height = h;
            nodes = n;
        }
    }

    public static Info full(Node x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info leftData = full(x.left);
        Info rightData = full(x.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        int nodes = leftData.nodes + rightData.nodes + 1;
        return new Info(height, nodes);
    }
}
