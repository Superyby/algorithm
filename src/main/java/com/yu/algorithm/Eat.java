package com.yu.algorithm;

public class Eat {
    //n份青草放在一堆
    //先手后手都绝顶聪明
    //string “先手” “后手”
    public static String winner1(int n ){
        if (n < 5){
            return (n == 2 || n == 0) ? "先手" : "后手";
        }
        // n >= 5
        int base = 1;
        while (base <= n){
            // 当前一共n份草 ， 先手吃掉的是base份，n - base是留给后手的草
            //
            if (winner1(n - base).equals("后手")){
                return "先手";
            }
            if (base > n / 4){
                break;
            }
            base *= 4;
        }
        return "后手";
    }

    public static String winner2(int n){
        if (n % 5 == 0 || n % 5 == 2){
            return "后手";
        }else {
            return "先手";
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 50; i++) {
            System.out.println(i + " : " + winner1(i));
        }
    }
}
