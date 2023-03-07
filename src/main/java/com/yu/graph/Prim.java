package com.yu.graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 要求无向图  从点考虑
 */
public class Prim {
    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> primMST(Graph graph) {
        //解锁的边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());

        HashSet<Node> set = new HashSet<>();
        Set<Edge> result = new HashSet<>(); //依次挑选的边在result里
        for (Node node : graph.nodes.values()) { //随便挑了一个点
            //node是开始点
            if (set.contains(node)) {// 如果在set中不存在node
                set.add(node);//加入
                for (Edge edge : node.edges) {//由一个点 解锁所有相邻的边
                    priorityQueue.add(edge); //把他的每一个边 加到队列
                }
                while (!priorityQueue.isEmpty()) {
                    Edge edge = priorityQueue.poll(); // 弹出解锁的边中最小的边
                    Node toNode = edge.to;//可能的一个新的点
                    if (!set.contains(toNode)) {
                        set.add(toNode); // 添加虽然可能会添加到重复元素 但不影响
                        result.add(edge);
                        for (Edge nextEdge : toNode.edges) {
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
        }
        return result;
    }
}
