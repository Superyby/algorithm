package com.yu.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 数组差值为2的数
 */
public class SubvalueEqualK {
    public static List<List<Integer>> allPair(int[] arr, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (Integer cur : set) {
            if (set.contains(cur + k)) {
                res.add(Arrays.asList(cur, cur + k));
            }
        }
        return res;
    }
}
