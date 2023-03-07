package com.yu.algorithm;

import java.util.ArrayList;

public class PrintAllPermutation {

    /**
     * str[i..]范围上，所有的字符，都可以在i位置上，后续都去尝试
     * str[0...i-1]范围上，是之前做的选择
     * 请把所有的字符串形成的全排列，加到res去
     *
     * @param str
     * @param i
     * @param res
     */
    public static void process(char[] str, int i, ArrayList<String> res) {
        if (i == str.length) {
            res.add(String.valueOf(str));
        }
        boolean[] visit = new boolean[26];
        for (int j = 0; j < str.length; j++) {
            if (!visit[str[j] - 'a']) { //去重
                visit[str[j] - 'a'] = true;
                swap(str, i, j);
                process(str, i + 1, res);
                swap(str, i, j);
            }
        }
    }

    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }
}
