package com.yu.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 宽度优先遍历   横着
 * 深度优先遍历  -- 二叉树先序遍历
 */
public class BFS {
    class Node<V> {
        V value;
        Node left;
        Node right;
    }

    public static void bfsTree(Node head) {  //宽度优先遍历
        if (head == null) {// 判断条件
            return; //为空返回
        }
        Queue<Node> queue = new LinkedList<>();// 创建一个队列
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    //宽度优先遍历   求  二叉树最大宽度
    public static int bfsTreeLevel(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);//将开始的节点放入
        int curLevel = 1; //当前层数
        int curLevelNodes = 0;//当前层数的节点数
        int max = Integer.MIN_VALUE;//全局最大宽度
        while (!queue.isEmpty()) {
            Node cur = queue.poll();//先弹出来一个元素
            int curNodeLevel = levelMap.get(cur);//得到当前元素的层数
            if (curNodeLevel == curLevel) { //当前节点的层数 == 当前层数  说明是同一层的元素
                curLevelNodes++; //本层节点++  也即是宽度++
            } else {
                max = Math.max(max, curLevelNodes); //不相等那就是取到了下一层的节点  直接结算上一层  在max和curLevelNodes取最大值
                curLevel++; //既然都取到了下一层  那直接给curLevel++
                curLevelNodes = 1; //到了新的一层  节点从1开始计算
            }
            if (cur.left != null) {//有左子树  在队列和map中加入
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {//同理
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
        }
        return max;
    }
}
