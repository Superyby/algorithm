package com.yu.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断是否是一颗完全二叉树
 */
public class IsCompleteBinaryTree {
    class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isCBT(Node head) {
        if (head == null) {
            return true; //空树  是
        }
        // 完全二叉树  宽度优先遍历
        boolean leaf = false;  //判断两个孩子不全   在遇到不双全之后 后面的应该为叶才对
        Node l = null;
        Node r = null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if ((l == null && r != null) || (leaf && (l != null || r != null))) {
                // 前条件  有右节点 无左节点  直接false
                // 后条件   在已经发现左右不双全的节点  且 此时的节点有孩子节点  也就是 如果遇到了不双全节点后 发现当前节点居然有孩子
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) { //不双全
                leaf = true;
            }
        }
        return true;
    }
}
