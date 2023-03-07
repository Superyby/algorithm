package com.yu.algorithm;

import java.util.HashMap;

/**
 * 给定一个字符串类型数组arr，求其中出现次数最多的前K个
 */
public class TopKTimes {

    //堆上放的类型是node实例
    public static class Node {
        public String str;
        public int times;

        public Node(String s, int t) {
            str = s;
            times = t;
        }
    }

    public static class TopKRecord {
        private HashMap<String, Node> strNodeMap; // 词频在Node中(times)  词频表
        private Node[] heap; // node类型的堆  小根堆
        private int heapSize;
        private HashMap<Node, Integer> nodeIndexMap; // 是否在堆上  不在是-1  在的话就是索引   堆位置表

        public void add(String str) {
            Node curNode = null; // Node节点
            int preIndex = -1; // 状态变量
            // 如果当前的表里没有node 创建出来 然后设置为-1
            if (!strNodeMap.containsKey(curNode)) {
                curNode = new Node(str, 1); // 创建出来新的节点 str , 出现了一次
                // 然后放在表中  记住  要修改就得两个表一起修改
                nodeIndexMap.put(curNode, -1); // 先设置为-1
                strNodeMap.put(str, curNode); // (字符串 ， 对应节点)
            } else {
                // 存在
                curNode = strNodeMap.get(str); // 直接拿出来 把值赋给新创建的curNode
                curNode.times++;
                preIndex = nodeIndexMap.get(curNode); // 在堆位置表里把它的位置给查出来
            }
            if (preIndex == -1) {
                // 没在堆上 分两种情况
                // 1.堆满了
                if (heapSize == heap.length) {
                    // 比较  看他能否把堆顶挤下去
                    if (heap[0].times < curNode.times) {
                        nodeIndexMap.put(heap[0], -1); // 拿走 设置为-1
                        nodeIndexMap.put(curNode, 0); //放到0位置
                        heap[0] = curNode;
                        heapify(0, heapSize);
                    }
                } else {
                    // 2. 堆没满 那就直接加
                    nodeIndexMap.put(curNode, heapSize);
                    heap[heapSize] = curNode;
                    heapInsert(heapSize++);
                }
            } else {
                heapify(preIndex, heapSize);
            }
        }

        public void heapInsert(int index) {
            while (index != 0) {
                int parent = (index - 1) / 2;
                if (heap[index].times < heap[parent].times) {
                    swap(parent, index);
                    index = parent;
                } else {
                    break;
                }
            }
        }

        public void heapify(int index, int heapSize) {
            int l = index * 2 + 1;
            int r = index * 2 + 2;
            int smallest = index;
            while (l < heapSize) {
                if (heap[l].times < heap[index].times) {
                    smallest = l;
                }
                if (r < heapSize && heap[r].times < heap[smallest].times) {
                    smallest = r;
                }
                if (smallest != index) {
                    swap(smallest, index);
                } else {
                    break;
                }
                index = smallest;
                l = index * 2 + 1;
                r = index * 2 + 2;
            }
        }

        public void swap(int index1, int index2) {
            nodeIndexMap.put(heap[index1], index2);
            nodeIndexMap.put(heap[index2], index1);
            Node tmp = heap[index1];
            heap[index1] = heap[index2];
            heap[index2] = tmp;
        }
    }
}
