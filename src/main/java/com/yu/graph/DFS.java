package com.yu.graph;

import java.util.HashSet;
import java.util.Stack;

/**
 * 深度优先遍历
 */
public class DFS {
    public static void dfs(Node node){
        if (node == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                while (!set.contains(next)){
                    stack.push(cur);//重新压栈回去
                    stack.push(next);//当前节点的下一节点压进栈
                    set.add(next);
                    System.out.println(next.value);
                    break;//这里的break的意思是 找到下一个节点  且这个节点没走过 好 先不看剩下的相邻节点 把这个节点先走到底。
                }
            }
        }
    }
}
