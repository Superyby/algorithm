package com.yu.algorithm;

/**
 * 判断一个32位数是否是 2的幂，4的幂
 */
public class Power {
    public static boolean is2Power(int n) {
        return (n & (n - 1)) == 0;
    }

    public static boolean is4Power(int n) {
                                        // 010101...010101
        return (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
    }
}
