package com.yu.algorithm;

public class UniqueBST {
    public static int process(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int res = 0;
        for (int leftNum = 0; leftNum < n - 1; leftNum++) {
            int leftWays = process(leftNum);
            int rightWays = process(n - 1 - leftNum);
            res += leftWays * rightWays;
        }
        return res;
    }

    public static int numTrees(int n) {
        if (n < 2) {
            return 1;
        }
        int[] dp = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j <= i - 1; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}
