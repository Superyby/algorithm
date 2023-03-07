package com.yu.treedp;

import java.util.List;

/**
 * 题目二<br></br>
 * 派对的最大快乐值
 * class Employee{
 * public int happy; //这名员工可以带来的快乐值
 * List<Employee> subordinates; // 这名员工有哪些直接下级
 * }
 * 1. 如果某个员工来了 ， 那么这个员工所有直接下级都不来<br></br>
 * 2. 派对的整体快乐值是所有到场员工快乐值的累加
 * 3. 你的目标是让派对的快乐值尽量最大
 */
public class MaxHappy {
    public static class Employee {
        public int happy;
        public List<Employee> nexts;
    }

    public static class Info {
        public int laiMaxHappy;
        public int buMaxHappy;

        public Info(int lai, int bu) {
            laiMaxHappy = lai;
            buMaxHappy = bu;
        }
    }

    //主函数
    public static int maxHappy(Employee boss) {
        Info headInfo = process(boss);
        return Math.max(headInfo.laiMaxHappy, headInfo.buMaxHappy);
    }

    public static Info process(Employee x) {
        if (x.nexts.isEmpty()) {
            return new Info(x.happy, 0);
        }
        int lai = x.happy;
        int bu = 0;
        for (Employee next : x.nexts) {
            Info nextInfo = process(next);
            lai += nextInfo.buMaxHappy;
            bu += Math.max(nextInfo.laiMaxHappy, nextInfo.buMaxHappy);
        }
        return new Info(lai, bu);
    }
}
