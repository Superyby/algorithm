package com.yu.algorithm;

import java.util.*;

/**
 * 全排列 ii
 * [47] LeetCode
 */
public class FullArrangement {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        int[] nums1 = {1, 2, 3};
//        System.out.println(permuteRepeat(nums));
        System.out.println(permuteUnique(nums));
    }

    // 主函数(全排列 有重复版本)
    public static List<List<Integer>> permuteRepeat(int[] nums) {
        int len = nums.length; // 数组长度
        List<List<Integer>> res = new ArrayList<>(); // 结果集
        if (len == 0) { // 如果长度为0 直接返回
            return res;
        }
        // 使用过的数字 长度跟nums一样
        boolean[] used = new boolean[len];  // 每次使用之后，标记为使用过
        List<Integer> path = new ArrayList<>(); // 状态变量
        dfs(nums, len, 0, path, used, res);
        return res;
    }

    // 遍历函数
    public static void dfs(int[] nums, int len, int depth, List<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;

                dfs(nums, len, depth + 1, path, used, res);
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * 全排列2  去重复版本
     * 重复的原因：数组中有重复的元素
     */

    public static List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        // 保存结果
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        // 排序
        Arrays.sort(nums);
        // 使用过的数字 长度跟nums一样
        boolean[] used = new boolean[len];
        Deque<Integer> path = new ArrayDeque<>(len); // 状态变量
        dfs(nums, len, 0, path, used, res);
        return res;
    }

    public static void dfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (used[i]) { // 还未使用
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
                continue;
            }

            path.addLast(nums[i]);
            used[i] = true;

            dfs(nums, len, depth + 1, path, used, res);
            used[i] = false;
            path.removeLast();
        }
    }
}
