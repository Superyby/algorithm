package com.yu.hash;

import java.util.HashMap;

/**
 * 写一个结构 加入 删除 返回随机数O(1)
 */
public class RandomPool {
    public static class Pool<K> {
        private HashMap<K, Integer> keyIndexMap; //key -->index
        private HashMap<Integer, K> indexKeyMap;//index --> key
        private int size;

        public Pool() {
            this.keyIndexMap = new HashMap<K, Integer>();
            this.indexKeyMap = new HashMap<Integer, K>();
            this.size = 0;
        }

        public void insert(K key) {
            if (!this.keyIndexMap.containsKey(key)) {
                this.keyIndexMap.put(key, this.size);//解释：key 是 第size个加入的
                this.indexKeyMap.put(this.size++, key);//解释：第size个进来的是key 然后size++
            }
        }

        public K getRandom() {
            if (this.size == 0) {
                return null;
            }
            int randomIndex = (int) (Math.random() * this.size); //0~size-1等概率随机返回
            return this.indexKeyMap.get(randomIndex);//在index-->key的map中把index拿出来  就做到字符串的等概率随机了
        }

        //删除元素  要用最后一个补上来  保持连续
        public void delete(K key) {
            if (this.keyIndexMap.containsKey(key)) {
                int deleteIndex = this.keyIndexMap.get(key);
                int lastIndex = --this.size;
                K lastKey = this.indexKeyMap.get(lastIndex);
                this.keyIndexMap.put(lastKey, deleteIndex);
                this.indexKeyMap.put(deleteIndex, lastKey);
                this.keyIndexMap.remove(key);
                this.indexKeyMap.remove(lastIndex);
            }
        }
    }
}
