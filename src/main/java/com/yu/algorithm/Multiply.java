package com.yu.algorithm;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 */
public class Multiply {
    /**
     * 方法一：做加法
     */
    public static String multiply(String num1, String num2) {
        // 先判断输入是否符合要求
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String res = "0"; // 结果
        int mlen = num1.length(); // num1长度
        int nlen = num2.length(); // num2长度
        for (int i = nlen - 1; i >= 0; i--) {
            StringBuffer curr = new StringBuffer();
            int add = 0;
            for (int j = nlen - 1; j > i; j--) {
                curr.append(0); //
            }
            int y = num2.charAt(i) - '0';
            for (int j = mlen - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int product = x * y + add;
                curr.append(product % 10);
                add = product / 10;
            }
            if (add != 0) {
                curr.append(add % 10);
            }
            res = addString(res, curr.reverse().toString());
        }
        return res;
    }

    public static String addString(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int add = 0;
        StringBuffer res = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            res.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        res.reverse();
        return res.toString();
    }

    /**
     * 解法二：普通竖式
     * 遍历 num2 每一位与 num1 进行相乘，将每一步的结果进行累加。
     * 注意：
     * num2 除了第一位的其他位与 num1 运算的结果需要 补0
     */
    public static String byNormalVertical(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        // 接收结果
        String res = "0";
        // num2逐位与num1相乘
        for (int i = num2.length() - 1; i >= 0; i--) { // 长度限制为num2的
            int carry = 0;
            // 保存num2第i位数字和num1相乘的结果
            StringBuffer temp = new StringBuffer();
            // 补0
            for (int j = 0; j < num2.length() - 1 - i; j++) {
                temp.append(0);
            }
            int n2 = num2.charAt(i) - '0';

            // num2 的第 i 位数字 n2 与 num1 相乘
            for (int j = num1.length() - 1; j >= 0 || carry != 0; j--) {
                int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
                int product = (n1 * n2 + carry) % 10;
                temp.append(product);
                carry = (n1 * n2 + carry) / 10;
            }
            // 将当前结果与新计算的结果求和作为新的结果
            res = addStringByNormalVertical(res, temp.reverse().toString());
        }
        return res;
    }

    /**
     * 对两个字符串数字进行相加，返回字符串形式的和
     */
    public static String addStringByNormalVertical(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0 || carry != 0; i--, j--) {
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            int sum = (x + y + carry) % 10;
            builder.append(sum);
            carry = (x + y + carry) / 10;
        }
        return builder.reverse().toString();
    }

    /**
     * 优化竖式
     * 该算法是通过两数相乘时，乘数某位与被乘数某位相乘，与产生结果的位置的规律来完成。具体规律如下：
     * 乘数 num1 位数为 MMM，被乘数 num2 位数为 NNN， num1 x num2 结果 res 最大总位数为 M+N
     * num1[i] x num2[j] 的结果为 tmp(位数为两位，"0x","xy"的形式)，其第一位位于 res[i+j]，第二位位于 res[i+j+1]。
     */
    public static String byOptimizingVertical(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0'; // i位置的那个值拿出来(num1)
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0'; // j位置的值拿出来(num2)
                int sum = (res[i + j + 1] + n1 * n2);
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) {
                continue;
            }
            result.append(res[i]);
        }
        return result.toString();
    }


    public static void main(String[] args) {
        String a = "123";
        String b = "456";
//        System.out.println(a.charAt(0) - '0');
//        System.out.println(a.length());
        System.out.println(multiply(a, b));
        System.out.println(byNormalVertical(a, b));
        System.out.println(byOptimizingVertical(a , b));
    }
}
