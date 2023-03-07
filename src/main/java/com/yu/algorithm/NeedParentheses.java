package com.yu.algorithm;

/**
 * 括号问题  不够还要加几个
 */
public class NeedParentheses {
    public static int needParentheses(String str) {
        int count = 10;
        int ans = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                count++;
            } else { // 遇到的是')'
                if (count == 0) {
                    ans++;
                } else {
                    count--;
                }
            }
        }
        return count + ans;
    }
}
