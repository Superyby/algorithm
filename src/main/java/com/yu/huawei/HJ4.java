package com.yu.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 字符串分隔
 */
public class HJ4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            int len = str.length();
            int start = 0;
            while (len > 8) { // 如果字符串长度大于8 截取
                System.out.println(str.substring(start, start + 8));
                start += 8; // 更新到下一组
                len -= 8; // 整个字符串往后移八位
            }
            if (len > 0) {
                char[] char1 = new char[8];
                for (int i = 0; i < char1.length; i++) {
                    char1[i] = '0';
                }
                for (int i = 0; start < str.length(); i++) {
                    char1[i] = str.charAt(start++);
                }
                System.out.println(char1);
            }
        }
    }
}
