package com.yu.algorithm;

/**
 * 给定两个长度都为N的数组weights和values，weights[i] 和 values[i]分别代表i号物品的重量和价值。给定一个正数bag，表示一个载重bag的袋子，你装的物品不能超过这个重量。返回你能装下最多的价值是多少
 */
public class Knapsack {

    /**
     * i...  往后的货物自由选择 ，形成最大价值返回
     * 永远不能超过bag
     * 之前做的决定，所达到的重量，alreadyweight
     */
    public static int process1(int[] weights, int[] values, int i, int alreadyweight, int bag) {
        if (alreadyweight > bag) {
            return 0;
        }
        if (i == weights.length) { //终止位置 没货物了
            return 0;
        }
        return Math.max(
                process1(weights, values, i + 1, alreadyweight, bag),
                values[i] + process1(weights, values, i + 1, alreadyweight + weights[i], bag)
        );
    }
}
