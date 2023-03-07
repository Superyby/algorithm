package com.yu.OrderedTableAndSetQueryEtc;

/**
 * 岛问题
 * O(N*M)
 */
public class IsLands {
    public static int countIslands(int[][] m){
        if (m == null || m[0] == null){
            return 0;
        }
        int N = m.length;//行号
        int M = m[0].length;//列号
        int res = 0;//岛的数量

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (m[i][j] == 1){
                    res++;
                    infect(m , i , j ,N ,M);
                }
            }
        }
        return res;
    }
    public static void infect(int[][] m , int i, int j, int N , int M){
        if (i < 0 || i >= N || j < 0 || j >= M || m[i][j] != 1){
            //行 < 0 || 行到了终止位置 || 列 < 0 || 列到了终止位置 || 所在的位置不是1
            return;
        }
        // i , j没越界 ，并且当前位置是1
        m[i][j] = 2;
        //上下左右递归
        infect(m , i + 1 , j , N , M);
        infect(m , i - 1 , j , N , M);
        infect(m , i , j + 1 , N , M);
        infect(m , i , j - 1 , N , M);
    }
}
