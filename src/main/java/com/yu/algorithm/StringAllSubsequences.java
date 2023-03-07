package com.yu.algorithm;

import java.util.List;

/**
 * 打印一个字符串的全部子序列，包括空字符串
 * 从左往右 字符要跟不要 做决策 结构类似于二叉树
 */
public class StringAllSubsequences {
    //当前来到i位置，要和不要，走两条路
    //res之前的选择，所形成的列表
    public static void process(char[] str, int i, List<Character> res) {
        if (i == str.length) {//i已经到了终止位置
            printList(res);//之前做的选择打印
            return;
        }
        List<Character> resKeep = copyList(res);
        resKeep.add(str[i]);
        process(str, i + 1, resKeep);//要当前字符的路
        List<Character> resNoInclude = copyList(res);
        process(str, i + 1, resNoInclude);//不要当前字符的路
    }

    public static void printList(List<Character> res) {
        System.out.println(res);
    }

    public static List<Character> copyList(List<Character> list) {
        return null;
    }

    /**
     * 另一种方法
     * 来到当前i的位置，要和不要，走两条路
     * 之前的选择，所形成的结果，是str
     *
     * @param str
     * @param i
     */
    public static void process(char[] str, int i) {
        if (i == str.length) {
            System.out.println(String.valueOf(str));
            return;
        }
        process(str, i + 1);//要当前字符的路
        char tmp = str[i];
        str[i] = 0;
        process(str, i + 1);//不要当前字符的路
        str[i] = tmp;
    }
}
