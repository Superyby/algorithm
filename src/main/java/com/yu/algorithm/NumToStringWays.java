package com.yu.algorithm;

/**
 * 将给定的数转换为字符串，原则如下，1对应a，2对应b，...26对应Z，例如12258，可以转换为"abbeh"，"aveg"，"abyh" , "lbeh" ,"lyh"个数为5，编写一个函数，给出可以转换的不同字符串的个数。
 */
public class NumToStringWays {
    public static int convertWays(int num) {
        if (num < 1) {
            return 0;
        }
        return process(String.valueOf(num).toCharArray(), 0);
    }

    /**
     * @param str   str[index......] 能转换出多少种有效的字符串表达
     * @param index 0......index已经转换完成，并且转换正确
     * @return 返回个数
     */
    public static int process(char[] str, int index) {
        if (index == str.length) {
            return 1;
        }
        //开头为0字符  返回0
        if (str[index] == '0') {
            return 0;
        }
        // index及其后续是还有数字字符的，且不以0开头，以1-9开头
        int res = process(str, index + 1); //做了一个决定，就让str[index]自己作为一部分
        if (index == str.length - 1) { //除了index外 后边没字符了  （到最后了）
            return res;
        }
        // index + 1不越界
        // index和index + 1共同构成一部分 < 27
        if (((str[index] - '0') * 10 + str[index + 1] - '0') < 27) {
            res += process(str, index + 2);
        }
        return res;
    }

    public static int dpWays(int num) {
        if (num < 1) {
            return 0;
        }
        char[] str = String.valueOf(num).toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        dp[N - 1] = str[N - 1] == '0' ? 0 : 1;
        for (int i = N - 2; i >= 0; i--) {
            if (str[i] == '0') {
                dp[i] = 0;
            } else {
                dp[i] = dp[i + 1] + (((str[i] - '0') * 10 + str[i + 1] - '0') < 27 ? dp[i + 2] : 0);
            }
        }
        return dp[0];
    }
}
