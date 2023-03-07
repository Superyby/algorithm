package com.yu.graph;

public class GraphGenerator {
    public static Graph createGraph(Integer[][] matrix) { //原始数据为二维数组  一个元素有三个值  分别为from , to , weight
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            Integer from = matrix[i][0]; //from点
            Integer to = matrix[i][1];   //to点           from to 的意思是 从哪个点到哪个点
            Integer weight = matrix[i][2];//权重
            if (!graph.nodes.containsKey(from)) {//如果图中还不存在from点  加进去
                graph.nodes.put(from, new Node(from)); //参数1：编号 参数2：对应的“城市”
            }
            if (!graph.nodes.containsKey(to)) { //同上
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from); //从点集里把from实际的点拿出来
            Node toNode = graph.nodes.get(to);     //把图中 to点拿出来
            Edge newEdge = new Edge(weight, fromNode, toNode); // 建立边
            fromNode.nexts.add(toNode); // from点的发散集合中加入 to  但是to 中不加from  因是有向图
            fromNode.out++; //出度++
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }
}
