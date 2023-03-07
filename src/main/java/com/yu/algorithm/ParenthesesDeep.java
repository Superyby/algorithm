package com.yu.algorithm;

/**
 *
 */
public class ParenthesesDeep {
    public static int maxLength(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[] dp = new int[str.length];
        int res = 0;
        int pre = 0;
        for (int i = 1; i < str.length; i++) {
            if (str[i] == ')') { // 左括号的话就不用管了
                pre = i - dp[i - 1] - 1;//与str[i]匹配的左括号位置 left
                if (pre >= 0 && str[pre] == '(') { // 看left位置是否越界，另一方面看它是不是配对的左括号
                    // 如果成立 i位置的值起码是dp[i - 1] + 2 ；然后看要不要往前接一段
                    //pre > 0 ? dp[pre - 1] : 0  如果有pre-1位置 那就接  没有 0
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            res = Math.max(res, dp[i]); //dp中的最大值就是解
        }
        return res;
    }
}
