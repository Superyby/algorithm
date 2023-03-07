package com.yu.algorithm;

/**
 * 数组全排列 回溯
 */

import java.util.ArrayList;
import java.util.List;

public class ArrangingAllArrays {
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) { // 判断条件
            return res;
        }

        boolean[] used = new boolean[len]; // 使用过的记录
        List<Integer> path = new ArrayList<>(); // 保存所需要的状态变量，path就相当于一个栈  记录当前状态之后，path.remove(path.size() - 1);到前一个 然后继续递归    全局使用一份状态变量。

        dfs(nums, len, 0, path, used, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, List<Integer> path, boolean[] used, List<List<Integer>> res) {
        System.out.println(path); // 更好的展示全过程
        if (depth == len) { // 递归终止条件
//            res.add(path);错误---> 变量 path 所指向的列表 在深度优先遍历的过程中只有一份 ，深度优先遍历完成以后，回到了根结点，成为空列表。
//            在 Java 中，参数传递是 值传递，对象类型变量在传参的过程中，复制的是变量的地址。这些地址被添加到 res 变量，但实际上指向的是同一块内存地址，因此我们会看到 666 个空的列表对象。解决的方法很简单，在 res.add(path); 这里做一次拷贝即可。
            res.add(new ArrayList<>(path));
            return;
        }

        // 在非叶子结点处，产生不同的分支，这一操作的语义是：在还未选择的数中依次选择一个元素作为下一个位置的元素，这显然得通过一个循环实现。
        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;

                dfs(nums, len, depth + 1, path, used, res);
                // 注意：下面这两行代码发生 「回溯」，回溯发生在从 深层结点 回到 浅层结点 的过程，代码在形式上和递归之前是对称的
                used[i] = false;
                path.remove(path.size() - 1); // 用完之后 还得删除
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        ArrangingAllArrays arrangingAllArrays = new ArrangingAllArrays();
        List<List<Integer>> lists = arrangingAllArrays.permute(nums);
        System.out.println(lists);
    }
}
