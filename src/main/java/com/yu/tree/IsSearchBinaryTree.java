package com.yu.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 是否是一颗搜索二叉树(对于每一棵子树来说他的左树的节点都比他小  右树的节点都比他大)  中序遍历总是升序  就是
 */
public class IsSearchBinaryTree {
    public static class Node {
        int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value = data;
        }
    }

    public static void checkBST(Node head) {
        List<Node> inOrderList = new ArrayList<>();
        process2(head, inOrderList);
        // TODO
        //便利inOrderList  看是否是升序
    }

    public static void process2(Node head, List<Node> inOrderList) {
        if (head == null) {
            return;
        }
        process2(head.left, inOrderList);
        inOrderList.add(head);
        process2(head.right, inOrderList);
    }


    /**
     * 第二种方式
     */
    public static int preValue = Integer.MIN_VALUE;

    public static boolean checkBST2(Node head) {
        if (head == null) {  //空树  是
            return true;
        }
        boolean isLeftBst = checkBST2(head.left);
        if (!isLeftBst) { //左树不是搜索二叉树
            return false;
        }
        if (head.value <= preValue) {
            return false;
        } else {
            preValue = head.value;
        }
        return checkBST2(head.right);
    }

    // 第三种方式  非递归
    public static boolean inOrderUnRecur1(Node head) {
        if (head != null) {
            int preValue = Integer.MIN_VALUE;
            Stack<Node> stack = new Stack<>();
            while (head != null || !stack.isEmpty()) {
                if (head != null) {  // 这一段if是把 左边界的元素加到栈里去    只要来到一个节点有左边界就一直加进去  没有左边界了就弹出一个节点 窜到右边之后再继续加左边界
                    stack.push(head);
                    head = head.left;
                } else {  //左边界全加了   先pop  然后依次往右动
                    head = stack.pop();
//                    System.out.println(head.value + " ");  打印行为换为处理方式
                    if (head.value <= preValue) {
                        return false;
                    } else {
                        preValue = head.value;
                    }
                    head = head.right;
                }
            }
        }
        return true;
    }

    //第三种  左树 搜✔ 右树 搜✔  左  max < x 右 min > x
    public static class ReturnData{
        public boolean isBST;
        public int min;
        public int max;
        public ReturnData(boolean is , int mi , int ma){
            isBST = is;
            min = mi;
            max = ma;
        }
    }

    public static ReturnData process(Node x){
        if (x == null){
            return null;
        }
        ReturnData leftData = process(x.left); //拿到左子树的返回值信息  3个
        ReturnData rightData = process(x.right); // 拿到右子树的返回值信息 3个
        int min = x.value; //刚开始 设置最小值为当前值
        int max = x.value;//刚开始  设置最大值为当前值
        if (leftData != null){//左树不为空
            min = Math.min(leftData.min , min);
            max = Math.max(leftData.max , max);
        }
        if ((rightData != null)){//右树不为空
            min = Math.min(rightData.min , min);
            max = Math.max(rightData.max, max);
        }
        boolean isBST = true;//先定义为二叉搜索树
        if (leftData != null && (!leftData.isBST || leftData.max >= x.value)){ //在左树不为空的情况下 左树不是平衡二叉树 或  左树最大值大于 当前值  isBST =  false;
            isBST =  false;
        }
        if (rightData != null && (!rightData.isBST || rightData.min <= x.value)){ //和上边一样
            isBST = false;
        }
        return new ReturnData(isBST , min , max);
    }
}
