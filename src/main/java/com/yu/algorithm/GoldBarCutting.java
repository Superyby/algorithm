package com.yu.algorithm;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 切金条问题
 * 一个金条切成两半 是需要花费和长度数值一样的铜板的，比如金条长度为20的金条  不管切成长度多大的两半  都要花费20个铜板
 * 有人分金条  怎么分最省铜板
 * 例如：给定数组{10,20,30},代表一共三个人，整块金条的长度为10+20+30=60
 * 金条分成10，20，30三个部分 ，如果先把长度为60的金条分成10和50 花费60 再把长度50的分成20和30，花费50 一共花费110铜板
 * 但如果先把长度60的分成30和30 花费60 再把长度30的分10和20，花费30  一共花90铜板
 * 输入一个数组 返回分割的最小代价
 */
public class GoldBarCutting {
    public static int lessMoney(int[] arr) {
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pQ.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        while (pQ.size() > 1) {//最后剩一个
            cur = pQ.poll() + pQ.poll(); //一次弹出两个数  加一下
            sum += cur;
            pQ.add(cur);//把弹出来的两个数的和重新加入到小根堆(PriorityQueue)
        }
        return sum;
    }
}
