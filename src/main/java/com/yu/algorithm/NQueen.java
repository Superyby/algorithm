package com.yu.algorithm;

/**
 * N皇后问题是指在N*N的棋盘上摆N个皇后，要求任何两个皇后不同行，不同列  也不在同一斜线
 * 给定一个整数n，返回n皇后的摆法有多少种
 * n=1 返回1
 * n=2或3 2皇后和3皇后问题无论怎么摆都不行 返回0
 * n=8 返回92
 */
public class NQueen {
    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];// i 行皇后放在了第几列
        return process1(0, record, n);
    }

    // 潜台词:record[0...i-1]皇后，任何两个皇后一定都不共行，不共列，不共斜线
    // i: 表示目前来到了第几行
    // record[0...i-1]:表示之前的行放皇后的位置
    // n:表示一共多少行
    // 返回值是: 摆完所有的皇后，合理的摆法有多少种
    public static int process1(int i, int[] record, int n) {
        if (i == n) { //终止行
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {//在当前行(i)，找所有元素(合适的)
            //当前i行的皇后，放在j列，会不会和之前(0...i-1)的皇后，共行或共列或共斜线
            //如果是 认为无效
            //如果不是 认为有效
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res; //合适的情况累加 就是答案
    }

    //record[0...i-1]需要看 ，record[i...]不需要看
    //返回i行皇后，放在了j列 是否有效
    public static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                //j == record[k]首先之前所有的皇后不能跟我的列是一个
                //Math.abs(record[k] - j) == Math.abs(i - k) 其次  列坐标的绝对值 == 之前某个皇后跟他行坐标减完的绝对值  说明斜率是45° 共斜线
                return false;
            }
        }
        return true;
    }


    //另一个版本 (优化)
    //不要超过32皇后
    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) {
            return 1;
        }
        int mostRightOne = 0;
        //所有可以填皇后的列     (|   不相同为1  相同为 0  ;  &  不相同为0 相同为 1   )  都在位信息上
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1); //把一个二进制中最右侧的1 提取出来
            pos = pos - mostRightOne;
            res += process2(limit, colLim | mostRightOne, (leftDiaLim | mostRightOne) << 1, (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }
}
