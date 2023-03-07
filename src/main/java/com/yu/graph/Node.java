package com.yu.graph;

import java.util.ArrayList;

public class Node {
    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nexts; // 当前点发散出去的边直接连的点有哪些
    public ArrayList<Edge> edges; //  属于当前点的边 (发散出去的边)

    public Node(int value){
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
