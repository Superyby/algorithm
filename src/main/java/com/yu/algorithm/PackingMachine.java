package com.yu.algorithm;

/**
 * 有n个打包机器从左到右依次排开，上方有一个自动装置会抓取一批放物品到每个打包机上，放到每个机器上的这些物品数量有多有少，由于物品数量不同，需要工人将每个机器上的物品进行迁移从而到达物品数量相等才能打包，每个物品重量太大，每次只能搬一个物品进行移动，为了省力，只在相邻的机器上移动，请计算在搬动最小次数的前提下，使每个机器上的物品数量相等，如果不能使每个机器上的物品相同，返回-1
 * 例如[1,0,5]表示有3个机器，每个机器上有1，0，5个物品，经过这些操作后，第一轮：1  0 <- 5 ==>  1 1 4 第二轮：1 <-1 <-4 ==> 2 1 3 第三轮：2 1 <- 3 ==> 2 2 2  移动了三轮，每个机器上物品数量相等，所以返回3
 * 例如 [ 2 , 2 ,3]表示有三台机器，每个机器上分别有2，2，3个物品，这些物品不管怎么移动，都不能使三个机器上物品数量相等  返回-1
 */
public class PackingMachine {
    public static int MInOps(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int size = arr.length;//多少个机器
        int sum = 0;//累加和
        for (int i = 0; i < size; i++) {
            sum += arr[i];
        }
        if (sum % size != 0) {
            return -1;
        }
        int avg = sum / size;
        int leftSum = 0;//左侧已经遍历过部分累加和
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            // 负  需要输入   正 需要输出
            int leftRest = leftSum - i * avg; // 0~i-1  有i台机器
            // 负  需要输入   正 需要输出
            int rightRest = (sum - leftSum - arr[i]) - (size - i - 1) * avg;
            if (leftRest < 0 && rightRest < 0) {// 如果都小于0 瓶颈就是他俩的绝对值相加
                ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
            } else { // 否则  左侧和右侧较大的
                ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
            }
            leftSum += arr[i];
        }
        return ans;
    }
}
