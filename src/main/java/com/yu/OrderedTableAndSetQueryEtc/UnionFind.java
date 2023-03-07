package com.yu.OrderedTableAndSetQueryEtc;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 并查集
 * <p>
 * 理解不难  难的是行动
 */
public class UnionFind {
    //样本进来会包一层，叫做元素
    public static class Element<V> {
        public V value; //用户给的样本 简单包装了一层

        public Element(V value) {
            this.value = value;
        }
    }

    public static class UnionFindSet<V> {
        //我给你一个样本(V)你给我找到加工出来的那个东西是啥(Element<V>)
        public HashMap<V, Element<V>> elementMap;
        //一个元素的父是谁
        public HashMap<Element<V>, Element<V>> fatherMap;
        //key表示某个集合的代表元素 ，value  该集合大小  (记录的是代表元素 和该集合有几个元素)
        public HashMap<Element<V>, Integer> sizeMap;

        public UnionFindSet(List<V> list) {//初始化的时候 要求用户把样本都给你
            //3个空表建出来
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value : list) {//对链表中的每一个样本
                Element<V> element = new Element<>(value);//包上一层圈
                elementMap.put(value, element);//样本(value) 对应自己的圈 (element)  存在elementMap
                fatherMap.put(element, element);//一上来每个元素集合只有自己  每个element 父  设置成自己
                sizeMap.put(element, 1);//因为每一个元素都是自己集合的代表元素  所在的大小都是1
            }
        }

        //给一个ele 往上一直找  把代表元素返回
        public Element<V> findHead(Element<V> element) {
            Stack<Element<V>> path = new Stack<>();
            while (element != fatherMap.get(element)) {//element不等于它的父节点(来到顶部了)
                path.push(element);//沿途的元素都加到栈中
                element = fatherMap.get(element);//往上
            }
            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), element);//把路径中所有元素的父节点 直接设置成对顶部的
            }
            return element;
        }

        public boolean isSameSet(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                //把a样本的圈拿出来(a圈可以通过fatherMap一直往上)就找到了所有他的head  b~
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }
            return false;
        }

        public void union(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                Element<V> aF = findHead(elementMap.get(a));//a圈往上到不能再往上 找到了他的元素的代表节点 (aF)
                Element<V> bF = findHead(elementMap.get(b));//~~~
                if (aF != bF) { //如果等于的话就是一个集合 不用union
                    //数量较小的集合的顶端 要挂在数量较多集合顶端的底下
                    Element<V> big = sizeMap.get(aF) >= sizeMap.get(bF) ? aF : bF;//谁是较大的一个呢
                    Element<V> small = big == aF ? bF : aF;//这两行就重定位了谁大谁小
                    //接下来 小的挂大的
                    fatherMap.put(small, big);//small是较小集合的代表节点  把它的父节点的走向改成 big
                    sizeMap.put(big, sizeMap.get(aF) + sizeMap.get(bF));//合并后的大小是两个集合的和大小
                    sizeMap.remove(small);//因为现在有了公共节点  所有原来的small要删掉 (因为small以后再也不是代表节点了)
                }
            }
        }
    }
}
