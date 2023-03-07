package com.yu.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PrintAllSubquences {
    public static List<String> subs(String s){//方便用户调用
        char[] str = s.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        process1(str , 0 , ans , path);
        return ans;
    }
    /**
     * @param str   字符串  固定不变
     * @param index 当前到哪儿了  no 或者 yes
     * @param ans   如果index来到了str的终止位置  把沿途路径所形成的答案，放入ans
     * @param path  之前做出的选择 就是path
     */
    public static void process1(char[] str, int index, List<String> ans, String path) {
        if (str.length == index) {//到最后了
            ans.add(path);
            return;
        }
        //没有到终止位置  继续
        //第一条路径
        String no = path;
        process1(str, index + 1, ans, no);//沿途路径不包含当前字符的路径

        //第二条路径
        String yes = path + String.valueOf(str[index]);//之前得到的path 加上当前index的字符
        process1(str, index + 1, ans, yes);//要了当前的字符
    }


    /**
     * 打印一个字符串的全部子序列，要求不出现重复字面值的子序列(加入HashSet)
     */
    public static List<String> subsNoRepeat(String s){ //方便用户调用
        char[] str = s.toCharArray();
        String path = "";
        HashSet<String> set = new HashSet<>();
        process2(str , 0 ,set , path);
        List<String> ans = new ArrayList<>();
        for (String cur : set) {
            ans.add(cur);
        }
        return ans;
    }
    public static void process2(char[] str , int index , HashSet<String> set , String path){
        if (str.length == index) {//到最后了
            set.add(path);
            return;
        }
        //没有到终止位置  继续
        //第一条路径
        String no = path;
        process2(str, index + 1, set, no);//沿途路径不包含当前字符的路径

        //第二条路径
        String yes = path + String.valueOf(str[index]);//之前得到的path 加上当前index的字符
        process2(str, index + 1, set, yes);//要了当前的字符
    }
}
