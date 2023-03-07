package com.yu.huawei;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * 字符串最后一个单词长度
 * 计算字符串最后一个单词的长度，单词以空格隔开，字符串长度小于5000。（注：字符串末尾不以空格为结尾）
 * 输入描述：
 * 输入一行，代表要计算的字符串，非空，长度小于5000。
 * <p>
 * 输出描述：
 * 输出一个整数，表示输入字符串最后一个单词的长度。
 * <p>
 * <p>
 * sc.next()和sc.nextLine()虽然都可以获取用户输入的信息，但是sc.next()是获取用户输入的第一个空格或者回车之前的字符串，如果是回车，那么会把回车符留下来，这就造
 * 成了一个坑。如果后续还有输入，那么我们接着读取的将会是一个空，而不是新输入的字符串。
 */
public class HJ1 {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()) {
//            String[] str = scanner.nextLine().split(" ");
//            System.out.println(str[str.length - 1].length());
//        }
//    }

    // 这个方法快就快在有空格 直接把空格前边的单词全不看了 （也就是len=0）最后就剩最后一个
    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        int len = 0;
        char c;
        while ('\n' != (c = (char) inputStream.read())) {
            ++len;
            if (c == ' ') {
                len = 0;
            }
        }
        System.out.println(len);
    }
}
