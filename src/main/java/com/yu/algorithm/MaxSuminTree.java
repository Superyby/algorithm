package com.yu.algorithm;

/**
 * 二叉树每个节点都有一个int权值，给定一个二叉树，要求计算出从根节点到叶节点的所有路径中，权值和最大值为多少
 */
public class MaxSuminTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int val) {
            value = val;
        }
    }

    public static int maxSum = Integer.MIN_VALUE;

    public static int maxPath(Node head) {
        p(head, 0);
        return maxSum;
    }

    //pre表示从跟节点出发当当前节点的上方节点获得的路径和
    public static void p(Node x, int pre) {
        if (x.left == null && x.right == null) {
            maxSum = Math.max(maxSum, pre + x.value);
        }
        if (x.left != null) {
            p(x.left, pre + x.value);
        }
        if (x.right != null) {
            p(x.right, pre + x.value);
        }
    }

    // x为头的整棵树上，最大路径和是多少，返回
    // 路径要求，一定从x出发，到叶节点，算作一个路径
    public static int process2(Node x) {
        if (x.left == null && x.right == null) {
            return x.value;
        }
        int next = Integer.MIN_VALUE;
        if (x.left != null) {
            next = process2(x.left);
        }
        if (x.right != null) {
            next = Math.max(next, process2(x.right));//与上面的值比较 取最大
        }
        return x.value + next;
    }
}
