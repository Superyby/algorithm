package com.yu.algorithm;

/**
 * 加减乘除
 */
public class AddMinusMultiDivideByBit {
    public static void main(String[] args) {
        int a = 1;
        int b = 5;
        System.out.println(add(a, b));
    }

    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b; //无进位相加的结果
            b = (a & b) << 1; //进位信息
            a = sum;
        }
        return sum;
    }

    public static int negNum(int n) {
        return add(~n, 1);
    }

    public static int minus(int a, int b) {
        return add(a, negNum(b));
    }

    public static int multi(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    //除法
}
