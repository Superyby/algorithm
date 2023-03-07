package com.yu.huawei;

import java.util.*;

/**
 * 明明的随机数
 * 描述
 * 明明生成了N个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。
 * <p>
 * 数据范围： 1 <= n <= 1000  ，输入的数字大小满足 1 <= val <= 500
 * <p>
 * 输入描述：
 * 第一行先输入随机整数的个数 N 。 接下来的 N 行每行输入一个整数，代表明明生成的随机数。 具体格式可以参考下面的"示例"。
 * 输出描述：
 * 输出多行，表示输入数据处理后的结果
 */
public class HJ3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // 拿到随机数的个数
        int[] num = new int[n];
        for (int i = 0; i < n; i++) { // 输入n个数字
            num[i] = in.nextInt();
        }
        res(num);
    }
    public static void res(int[] num){
        // 去重
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num.length; i++) {
            if (!list.contains(num[i])){
                list.add(num[i]);
            }
        }
        // 排序
        Collections.sort(list);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}
