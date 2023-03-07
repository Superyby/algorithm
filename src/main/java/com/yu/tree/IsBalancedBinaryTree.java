package com.yu.tree;

/**
 * 判断是否是平衡二叉树
 * 对于任何一个子树  他左树的高度和右树的高度差都不超过1
 */
public class IsBalancedBinaryTree {
    public static class Node {
        int value;
        public Node left;
        public Node right;
    }
    public static boolean isBalanced(Node head){
        return process(head).isBalanced;
    }
    public static class ReturnType{ //返回俩信息
        public boolean isBalanced;
        public int height;

        public ReturnType(boolean isB , int hei){
            isBalanced = isB;
            height = hei;
        }
    }

    public static ReturnType process(Node x){
        if (x == null){
            return new ReturnType(true , 0);
        }
        ReturnType leftdata = process(x.left);//左树是否是平衡二叉树
        ReturnType rightdata = process(x.right);

        int height  = Math.max(leftdata.height, rightdata.height) + 1;//算出自己的高度
        boolean isBalanced = leftdata.isBalanced && rightdata.isBalanced && Math.abs(leftdata.height - rightdata.height) < 2; // abs 绝对值

        return new ReturnType(isBalanced , height);
    }
}
