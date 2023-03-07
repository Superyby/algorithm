package com.yu.graph;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    public HashMap<Integer , Node> nodes; //编号及其对应的点
    public HashSet<Edge> edges;//图的边集

    public Graph(){
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
