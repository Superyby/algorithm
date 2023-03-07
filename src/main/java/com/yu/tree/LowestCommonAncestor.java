package com.yu.tree;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定node1  node2 返回最低公共祖先
 */
public class LowestCommonAncestor {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node lca(Node head, Node o1, Node o2) {
        HashMap<Node, Node> fmap = new HashMap<>();
        fmap.put(head, head);
        process(head, fmap);
        HashSet<Node> set1 = new HashSet<>();
        Node cur = o1;
        while (cur != fmap.get(cur)) { //如果cur != 自己的父节点
            set1.add(cur);
            cur = fmap.get(cur);
        }
        set1.add(head);
        // TODO 写一个while  o2每往上窜一步 就在set1检查存不存在当前节点
        return head;// 只是为了不报错
    }

    public static void process(Node head, HashMap<Node, Node> fmap) {
        if (head == null) {
            return;
        }
        fmap.put(head.left, head);//表示 head.left 节点的父节点是 head
        fmap.put(head.right, head);//表示 head.right 节点的父节点是 head
        process(head.left, fmap);//去他所有的左树上 设置fathermap
        process(head.right, fmap);//去他所有的右树上设置fathermap
    }


    //第二种方法  有些抽象
    public static Node lca2(Node head, Node o1, Node o2) {
        if (head == null || head == o1 || head == o2) {
            return head;
        }
        Node left = lca2(head.left, o1, o2);
        Node right = lca2(head.right, o1, o2);
        if (left != null && right != null) {
            return head;
        }
        return left != null ? left : right;
    }
}
