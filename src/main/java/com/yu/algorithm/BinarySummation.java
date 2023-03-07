package com.yu.algorithm;

/**
 * 二进制求和
 * 给定两个只含0 和 1字符串，返回结果二进制字符串
 */
public class BinarySummation {
    /**
     * 整体思路是将两个字符串较短的用 000 补齐，使得两个字符串长度一致，然后从末尾进行遍历计算，得到最终结果。
     * <p>
     * 本题解中大致思路与上述一致，但由于字符串操作原因，不确定最后的结果是否会多出一位进位，所以会有 2 种处理方式：
     * <p>
     * 第一种，在进行计算时直接拼接字符串，会得到一个反向字符，需要最后再进行翻转
     * 第二种，按照位置给结果字符赋值，最后如果有进位，则在前方进行字符串拼接添加进位
     * 时间复杂度：O(n)
     */
    public static String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int ca = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = ca;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            res.append(sum % 2);
            ca = sum / 2;
        }
        res.append(ca == 1 ? ca : "");
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        String a = "11";
        String b = "1";
        System.out.println(addBinary(a, b));
    }
}
