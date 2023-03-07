package com.yu.graph;

import java.util.*;

/**
 * 拓扑排序算法  适用范围：要求有向图  且有入度为0的节点  且没有环
 * 在有向图中找到入度为0的点 输出 把他的影响擦掉在剩下的图中依次这样找
 */
public class TopologicalSortingAlgorithm {
    public static List<Node> sortedTopology(Graph graph) {
        // key : 某一个node  value: 剩余的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        //入度为0的点才能进这个队列
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) { //先看一下整张图的每一个点
            inMap.put(node, node.in); //吧每一个点和他的入度 加入map
            if (node.in == 0) { //哪些点入度为0  直接加到zeroInQueue
                zeroInQueue.add(node);
            }
        }
        //拓扑排序结果  依次加入result
        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();//已经入度为0的点 取出一个
            result.add(cur); //把这个点加到拓扑排序的结果里去
            for (Node next : cur.nexts) { //擦除影响
                inMap.put(next, inMap.get(next) - 1); //对于每个cur来说 它的后续在imMap里入度  - 1
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}
