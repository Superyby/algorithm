package com.yu.graph;

import java.util.*;

/**
 * 针对无向图  生成最小生成树
 */
public class Kruskal {
    public static class MySets {
        public HashMap<Node, List<Node>> setMap;//一个点 对应一个集合

        public MySets(List<Node> nodes) {  //初始化的时候 把所有点都给它
            for (Node cur : nodes) { // 每个点都建立一个list  然后只把自己放进去  然后注册
                List<Node> set = new ArrayList<>();
                set.add(cur); //set中只放当前一个点
                setMap.put(cur, set); //注册到map
            }
        }

        public boolean isSameSet(Node from, Node to) { //判断from和to是否在同一个集合
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            return fromSet == toSet;
        }

        public void union(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            for (Node toNode : toSet) {
                fromSet.add(toNode);// toSet的所有节点都加到fromSet去
                setMap.put(toNode, fromSet);// 每一个to节点把他的list指向都指向了from   from和to现在都在一个集合 内存地址是一个
            }
        }

        //用于比较边长大小
        public static class EdgeComparator implements Comparator<Edge> {

            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }

        }

        public static Set<Edge> kruskalMST(Graph graph) {
            UnionFind unionFind = new UnionFind();
            unionFind.makeSets(graph.nodes.values());
            //此队列的作用是收集所有边，让边从小到大弹出
            PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
            for (Edge edge : graph.edges) {
                priorityQueue.add(edge);
            }
            Set<Edge> result = new HashSet<>();
            while (!priorityQueue.isEmpty()) {
                Edge edge = priorityQueue.poll();
                //如果这两个节点不在一个集合中，即没有形成环
                if (!unionFind.isSameSet(edge.from, edge.to)) {
                    result.add(edge);
                    //把这两个节点放到一个集合中
                    unionFind.union(edge.from, edge.to);
                }
            }
            return result;
        }
    }
}
