package com.yu.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 计算某字符出现的次数
 * 写出一个程序，接受一个由字母、数字和空格组成的字符串，和一个字符，然后输出输入字符串中该字符的出现次数。（不区分大小写字母）
 */
public class HJ2 {
    // 注意类名必须为 Main, 不要有任何 package xxx 信息
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        // 注意 hasNext 和 hasNextLine 的区别
//        while (in.hasNext()) { // 注意 while 处理多个 case
//            String a = in.nextLine(); // 接受字符串
//            String b = in.nextLine(); // 查找的字符
//            System.out.println(num(a, b));
//        }
//    }
//
//    public static int num(String a, String b) {
//        int res = 0;
//        String[] arr = a.split("");
//        for (String s : arr) {
//            if (s.equalsIgnoreCase(b)){
//                res++;
//            }
//        }
//        return res;
//    }

    // 这个方法快
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        char[] chars1 = bf.readLine().toLowerCase().toCharArray(); // 读取字符
        char[] chars2 = bf.readLine().toLowerCase().toCharArray(); // 读取目标字符
        int count = 0; // 记录结果
        for (int i = 0; i < chars1.length; i++) {
            if ((chars1[i] > 65 || chars1[i] < 90 ) && (chars1[i] == chars2[0])){
                count++;
            }
        }
        System.out.println(count);
    }
}
