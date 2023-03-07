package com.yu.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: project
 * @description: HashMap 的学习
 * @author: Yu
 * @create: 2023-02-02 09:19
 **/
public class Test01 {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("柳岩", 18);
        map.put("刘德华", 15);
        map.put("路建", 23);
        map.put("戚薇", 25);
        map.put("柳岩", 26);
        System.out.println(map);

        // 在底层通过一系列的右移运算和或  计算出最靠近边界值的2的n次幂
        /**
         *     static final int tableSizeFor(int cap) {
         *         int n = cap - 1;
         *         n |= n >>> 1;
         *         n |= n >>> 2;
         *         n |= n >>> 4;
         *         n |= n >>> 8;
         *         n |= n >>> 16;
         *         return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
         *     }
         */
        int cap = 10;
        int n = cap - 1; //--->9
        n |= n >>> 1;
        /**
         * 00000000 00000000 00000000 00001001   9
         * 00000000 00000000 00000000 00000100   9 >>> 1  右移---4
         * ---------------------------------------
         * 00000000 00000000 00000000 00001101   13 n
         * n |= n >>> 1;这个算法可以使得00000000 00000000 00000000 00001001 的相邻右边变为1
         */


        /**
         * threshold 计算公式：capacity(数组默认长度16)*loadFactor(负载因子默认0.75).这个值是当前已占用数组长度的最大值。当Size>=threshold时，那么就要考虑扩容，也就是说，这个就是衡量数组是否需要扩容的一个标准。扩容后的HashMap容量是之前的两倍
         */
//
//            public HashMap( int initialCapacity, float loadFactor){
//            if (initialCapacity < 0)
//                throw new IllegalArgumentException("Illegal initial capacity: " +
//                        initialCapacity);
//            if (initialCapacity > MAXIMUM_CAPACITY)
//                initialCapacity = MAXIMUM_CAPACITY;
//            if (loadFactor <= 0 || Float.isNaN(loadFactor))
//                throw new IllegalArgumentException("Illegal load factor: " +
//                        loadFactor);
//            this.loadFactor = loadFactor;
//            this.threshold = tableSizeFor(initialCapacity);
//        }


//          注意加1 是为了减少扩容
//        final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict) {
//            int s = m.size();
//            if (s > 0) {
//                if (table == null) { // pre-size
//                    float ft = ((float)s / loadFactor) + 1.0F;
//                    int t = ((ft < (float)MAXIMUM_CAPACITY) ?
//                            (int)ft : MAXIMUM_CAPACITY);
//                    if (t > threshold)
//                        threshold = tableSizeFor(t);
//                }
//                else if (s > threshold)
//                    resize();
//                for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
//                    K key = e.getKey();
//                    V value = e.getValue();
//                    putVal(hash(key), key, value, false, evict);
//                }
//            }
//        }

//          HashMap中put方法的实现
//        final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
//        boolean evict) {
//            HashMap.Node<K,V>[] tab; HashMap.Node<K,V> p; int n, i;
//            if ((tab = table) == null || (n = tab.length) == 0)
//                n = (tab = resize()).length;
//            if ((p = tab[i = (n - 1) & hash]) == null)
//                tab[i] = newNode(hash, key, value, null);
//            else {
//                HashMap.Node<K,V> e; K k;
//                if (p.hash == hash &&
//                        ((k = p.key) == key || (key != null && key.equals(k))))
//                    e = p;
//                else if (p instanceof HashMap.TreeNode)
//                    e = ((HashMap.TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
//                else {
//                    for (int binCount = 0; ; ++binCount) {
//                        if ((e = p.next) == null) {
//                            p.next = newNode(hash, key, value, null);
//                            if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
//                                treeifyBin(tab, hash);
//                            break;
//                        }
//                        if (e.hash == hash &&
//                                ((k = e.key) == key || (key != null && key.equals(k))))
//                            break;
//                        p = e;
//                    }
//                }
//                if (e != null) { // existing mapping for key
//                    V oldValue = e.value;
//                    if (!onlyIfAbsent || oldValue == null)
//                        e.value = value;
//                    afterNodeAccess(e);
//                    return oldValue;
//                }
//            }
//            ++modCount;
//            if (++size > threshold)
//                resize();
//            afterNodeInsertion(evict);
//            return null;
//        }
    }
}
