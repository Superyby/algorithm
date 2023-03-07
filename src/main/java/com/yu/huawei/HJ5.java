package com.yu.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 进制转换
 * 描述
 * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
 */
public class HJ5 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            // 先处理前两位
            String tmp = str.substring(2, str.length());
            int sum = 0;
            int len = tmp.length();
            for (int i = len - 1; i >= 0; i--) {
                char c = tmp.charAt(i);
                int tmpNum = (int) c;// ASCII码
                if (tmpNum >= 65) {
                    tmpNum = tmpNum - 65 + 10;
                } else {
                    tmpNum = tmpNum - 48;
                }
                sum = sum + (int) Math.pow(16, len - i - 1) * tmpNum;
            }
            System.out.println(sum);
        }
    }


//    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String str;
//        while ((str = reader.readLine()) != null) {
//            System.out.println(Long.parseLong(str.substring(2), 16));
//        }
//    }
}
