package com.yu.algorithm;

/**
 * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，x^n ）。
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 */
public class Pow {
    public static void main(String[] args) {
        System.out.println(myPow(2, 10));
    }

    public static double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public static double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }
}
