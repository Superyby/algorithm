package com.yu.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 适用范围：可以有权值为负数的边 但不能有累加和权值为负数的环
 * 单元最短路径算法
 */
public class Dijkstra {
    public static HashMap<Node, Integer> dijkstra(Node head) {
        //head 出发到所有位置的最小距离
        //key 从head出发到达key
        //value 从head出发到达key的最小距离
        //如果在表中没有T记录  含义是从head出发到T这个点的距离为正无穷
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(head, 0);
        //已经求过距离的节点，存在selectedNodes，以后再也不改
        HashSet<Node> selectedNodes = new HashSet<>();
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);//distanceMap找到一个最小的记录，我要处理，但是这个最小的记录不能是处理过的
        while (minNode != null) {
            int distance = distanceMap.get(minNode);//源节点到此时处理节点的距离就拿到了
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight);
                } else {
                    distanceMap.put(edge.to, Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    public static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> touchedNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!touchedNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }
}
