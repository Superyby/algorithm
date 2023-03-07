package com.yu.algorithm;

public class CoverToLetterString {
    /**
     * 数字 0-9  1-A  2-B ...  两位数可以表示一个字母 不超过26  多少种转化结果
     *
     * @param str
     * @param i
     * @return
     */
    public static int process(char[] str, int i) {
        if (i == str.length) {//如果i已经来到了最后的位置
            return 1;//返回一种有效的  这种有效的是之前做的决定
        }
        if (str[i] == '0') { //之前的调用是有效的  但是现在来了个0  转换到了个没法转换的状态
            return 0;
        }
        // 分情况  (1-2) (3-9)
        if (str[i] == '1') {
            int res = process(str, i + 1);//i自己作为独立的一部分  后续有多少种方法
            if (i + 1 < str.length) {
                res += process(str, i + 2);
            }
            return res;
        }
        if (str[i] == '2') {
            int res = process(str, i + 1);// i 将自己作为独立的一部分  有多少种方法
            if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
                res += process(str, i + 2);
            }
            return res;
        }
        // str[i] == '3' ~ '9'
        return process(str, i + 1);
    }
}
