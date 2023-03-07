package com.yu.algorithm;

/**
 * arr数组
 */
public class CoinsMin {
    public static int minCoins1(int[] arr, int aim) {
        return process1(arr, 0, aim);
    }

    public static int process1(int[] arr, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (rest == 0) {
            return 0;
        }
        // rest > 0
        if (index == arr.length) {
            return -1;
        }
        // rest > 0 并且 也有硬币
        int p1 = process1(arr, index + 1, rest);
        int p2Next = process1(arr, index + 1, rest - arr[index]);
        if (p1 == -1 && p2Next == -1) {
            return -1;
        } else {
            if (p1 == -1) {
                return p2Next + 1;
            }
            if (p2Next == -1) {
                return p1;
            }
            return Math.min(p1, p2Next);
        }
    }


    /**
     * 记忆化搜索 二维表
     */

    public static int minCoins2(int[] arr, int aim) {
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < aim; j++) {
                dp[i][j] = -2; //因为-1用了
            }
        }
        return process2(arr, 0, aim, dp);
    }

    public static int process2(int[] arr, int index, int rest, int[][] dp) {
        if (rest < 0) {
            return -1;
        }
        if (dp[index][rest] != -2) {
            return dp[index][rest];
        }
        // rest > 0
        if (rest == 0) {
            dp[index][rest] = 0;
        } else if (index == arr.length) {
            dp[index][rest] = -1;
        } else {
            int p1 = process2(arr, index + 1, rest, dp);
            int p2Next = process2(arr, index + 1, rest - arr[index], dp);

            if (p1 == -1 && p2Next == -1) {
                dp[index][rest] = -1;
            } else {
                if (p1 == -1) {
                    dp[index][rest] = p2Next + 1;
                } else if (p2Next == -1) {
                    dp[index][rest] = p1;
                } else {
                    return Math.min(p1, p2Next);
                }
            }
        }
        return dp[index][rest];
    }

    /**
     * 二维表
     */
    public static int minCoins3(int[] arr, int aim) {
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int row = 0; row <= N; row++) {
            dp[row][0] = 0;
        }
        for (int col = 0; col <= aim; col++) {
            dp[N][col] = -1;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int p1 = dp[index + 1][rest];
                int p2Next = -1; //注意不要越界
                if (rest - arr[index] >= 0) {
                    p2Next = dp[index + 1][rest - arr[index]];
                }
                if (p1 == -1 && p2Next == -1) {
                    dp[index][rest] = -1;
                } else {
                    if (p1 == -1) {
                        dp[index][rest] = p2Next + 1;
                    } else if (p2Next == -1) {
                        dp[index][rest] = p1;
                    } else {
                        dp[index][rest] = Math.min(p1, p2Next);
                    }
                }
            }
        }
        return dp[0][aim];
    }
}
