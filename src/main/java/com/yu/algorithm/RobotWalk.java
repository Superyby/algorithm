package com.yu.algorithm;


/**
 * 机器人走路  必须走k步  几种走法
 */
public class RobotWalk {


    /**
     * @param N 与下面函数对应
     * @param E 与下面函数对应
     * @param S 与下面函数对应
     * @param K 与下面函数对应
     * @return 与下面函数对应
     */
    public static int walkWays1(int N, int E, int S, int K) {
        return f1(N, E, K, S);
    }

    /**
     * 递归版本 无优化
     *
     * @param N    一共1~N这么多位置  固定参数
     * @param E    最终的目标位置是E  固定参数
     * @param rest 还剩下rest步可以走
     * @param cur  当前位置
     * @return 返回方法数
     */
    public static int f1(int N, int E, int rest, int cur) {
        //basecase 还剩下0步？ 在剩下0步的情况下  cur是否在目标位置E  是 算一种方法  不是  不算
        if (rest == 0) {
            return cur == E ? 1 : 0;
        }
        // rest > 0 有路可走  就要分是否在两头
        //在最左边 只能往2走
        if (cur == 1) {
            return f1(N, E, rest - 1, 2);
        }
        //在最右边  只能往N-1走
        if (cur == N) {
            return f1(N, E, rest - 1, N - 1);
        }
        //机器人在中间位置
        //分为向左走 和向右走两种情况相加
        return f1(N, E, rest - 1, cur + 1) + f1(N, E, rest - 1, cur - 1);
    }

    public static int walkWays2(int N, int E, int S, int K) {
        int[][] dp = new int[K + 1][N + 1];
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = -1;
            }
        }
        return f2(N, E, K, S, dp);
    }

    /**
     * 记忆化搜索   用缓存(dp) 如果之前算过当前位置  就跳过  不会重复计算
     *
     * @param N    与上面函数对应
     * @param E    与上面函数对应
     * @param rest 与上面函数对应
     * @param cur  与上面函数对应
     * @param dp   可以理解为缓存
     * @return 与上面函数对应
     * <p>
     * 最后把多个dp[rest][cur] 提取出来
     */
    public static int f2(int N, int E, int rest, int cur, int[][] dp) {
        if (dp[rest][cur] != -1)
            //不等于-1 说明算过了 跳过
            return dp[rest][cur];
        //缓存没命中
        if (rest == 0) {//跟上面一样  只不过要把值存在dp中
            dp[rest][cur] = cur == E ? 1 : 0; //存值
        } else if (cur == 1) {
            dp[rest][cur] = f1(N, E, rest - 1, 2);//存值
        } else if (cur == N) {
            dp[rest][cur] = f1(N, E, rest - 1, N - 1);//存值
        } else { //左 右 两种走法
            dp[rest][cur] = f1(N, E, rest - 1, cur + 1) + f1(N, E, rest - 1, cur - 1);//存值
        }
        return dp[rest][cur];//其实每个if都返回  提取出来
    }
}
