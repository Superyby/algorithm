package com.yu.algorithm;

/**
 * 假设  m 和 s初始化 ，s = "a" , m = s
 * 再定义两种操作，第一种操作：
 * m = s
 * s = s + s
 * 第二种操作
 * s = s + m
 * 求最小操作步骤数，可以将s拼接到长度等于n
 * <p>
 * 若n为质数   那么就只选择操作2
 * <p>
 * 解法： 把所有的质数因子全部找到，把质数因子全部累加起来 再-质数因子的个数
 */
public class SplitNbyNum {
    public static int[] divsSumAndCount(int n) {
        int sum = 0;
        int count = 0;
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                sum += i;
                count++;
                n /= i;
            }
        }
        return new int[]{sum, count};
    }

    public static int minOps(int n ){
        if (n < 2){
            return 0;
        }
        // TODO
//        if (isPrim(n)){
//            return n - 1;
//        }
        //n 不是质数
        int[] divsSumAndCount = divsSumAndCount(n);
        return divsSumAndCount[0] - divsSumAndCount[1];
    }
}
