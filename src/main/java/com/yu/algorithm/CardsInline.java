package com.yu.algorithm;

public class CardsInline {
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }              //在(0~n-1)范围先手(玩家1)       (0~n-1)后手玩家b
        return Math.max((f(arr, 0, arr.length) - 1), s(arr, 0, arr.length) - 1);//前者是玩家1  后者是玩家2   玩家1和2谁打返回谁
    }

    public static int f(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }
        return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
    }

    public static int s(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        }
        return Math.min(f(arr, i + 1, j), f(arr, i, j - 1));
    }

    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];
        for (int j = 0; j < arr.length; j++) {
            f[j][j] = arr[j];
            for (int i = j - 1; i >= 0; i--) {
                f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
                s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
            }
        }
        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
    }

    public static void main(String[] args) {
        int arr[] = {1, 3, 1};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
    }
}
