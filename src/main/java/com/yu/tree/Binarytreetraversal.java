package com.yu.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树遍历（非递归）
 */
public class Binarytreetraversal {
    public static class Node<V> {
        V value;
        Node left;
        Node right;
    }

    public static void main(String[] args) {
        List<Node> list = new LinkedList<>();
    }

    //二叉树先序遍历(非递归)  -- 头左右
    public static void preOrderUnRecur1(Node head) {
        if (head != null) { //头节点不为空
            Stack<Node> stack = new Stack<>(); //利用栈
            stack.add(head);//先把头节点加入到栈中
            while (!stack.isEmpty()) { //栈不为空  -- 先序遍历(头左右)  先加右子树  再加左子树  如果节点没有儿子节点  什么也不做  弹出
                head = stack.pop(); //弹出栈顶元素
                System.out.println(head.value + " "); //弹出来就打印(或者改为其他处理)
                if (head.right != null) { //先加入右节点  右节点不为空  加
                    stack.push(head.right);
                }
                if (head.left != null) {  //后加入左节点  左节点不为空  加
                    stack.push(head.left);
                }
            }
        }
    }

    //先序遍历  (递归)
    public static void preOrderUnRecur2(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value);
        preOrderUnRecur2(head.left);
        preOrderUnRecur2(head.right);
    }

    //二叉树后序遍历(非递归)  -- 左右头
    public static void posOrderUnRecur1(Node head) { //后序遍历 准备两个栈
        if (head != null) {  //条件  head是否为空
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();// 收集栈
            s1.push(head);//head放入 s1
            while (!s1.isEmpty()) { //判断s1是否为空
                head = s1.pop();// 将s1栈顶元素pop
                s2.push(head); //pop的元素加入s2
                if (head.left != null) { // 与先序遍历不一样的是  这个是先left  后  right
                    s1.push(head.left);  //加入s1
                }
                if (head.right != null) {  //~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    s1.push(head.right);  //~~~~~~~~~~~~~~~~~~~~~~~~
                }
            }
            while (!s2.isEmpty()) {  //操作s2  s2不为空
                System.out.println(s2.pop().value + " "); //执行操作
            }
        }
    }

    //后序遍历  (递归)
    public static void posOrderUnRecur2(Node head) {
        if (head == null) {
            return;
        }
        posOrderUnRecur2(head.left);
        posOrderUnRecur2(head.right);
        System.out.println(head.value);
    }


    //二叉树中序遍历(非递归)  --左头右
    public static void inOrderUnRecur1(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (head != null || !stack.isEmpty()) {
                if (head != null) {  // 这一段if是把 左边界的元素加到栈里去    只要来到一个节点有左边界就一直加进去  没有左边界了就弹出一个节点 窜到右边之后再继续加左边界
                    stack.push(head);
                    head = head.left;
                } else {  //左边界全加了   先pop  然后依次往右动
                    head = stack.pop();
                    System.out.println(head.value + " ");
                    head = head.right;
                }
            }
        }
    }

    //中序遍历(递归)
    public static void inOrderUnRecur2(Node head) {
        if (head == null) {
            return;
        }
        inOrderUnRecur2(head.left);
        System.out.println(head.value);
        inOrderUnRecur2(head.right);
    }
}
