package com.yu.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 中序遍历无法反序列化回去
 * eg:
 *           _2
 *          /
 *         1
 *         和
 *         1_
 *           \
 *            2
 *       补足空位置的中序遍历结果都是{null , 1 , null , 2 , null}
 */
public class Serialize {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int val){
            value = val;
        }
    }

    //以head为头节点  序列化成字符串返回  先序
    public static String serialByPre(Node head){
        if (head == null){
            return "#_";
        }
        String res = head.value + "_";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    //反序列化(先序)
    public static Node reconByPreString(String preStr){
        String[] values = preStr.split("_");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i != values.length; i++) {
            queue.add(values[i]);
        }
        return reconPreOrder(queue);
    }
    public static Node reconPreOrder(Queue<String> queue){
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }
}
