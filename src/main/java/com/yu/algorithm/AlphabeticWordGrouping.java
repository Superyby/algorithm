package com.yu.algorithm;

import java.util.*;

/**
 * 字母异位词分组
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 */
public class AlphabeticWordGrouping {
    /**
     * 如果互为异位词 那么排序之后应该是一样的  故可以将排序之后的字符串作为哈希表的键。
     * 哈希表的键为一组字母异位词的标志，哈希表的值为一组字母异位词列表。
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    public List<List<String>> groupAnagramsNew(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] count = new int[26];
            int len = str.length();
            for (int i = 0; i < len; i++) {
                count[str.charAt(i) - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (count[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(count[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
