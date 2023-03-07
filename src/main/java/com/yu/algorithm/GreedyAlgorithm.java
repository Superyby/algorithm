package com.yu.algorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 贪心算法
 * 在某一标准下  优先考虑最满足标准的样本  最后考虑 最不满足标准的样本  最终得到一个答案的算法，叫做贪心算法
 * 也就是说 不从整体最优上加以考虑  所作出的是在某种意义上的局部最优解
 * 局部最优  -->  整体最优
 */
public class GreedyAlgorithm {
    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class ProgramComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    public static int bestArrange(Program[] programs, int timePoint) {
        Arrays.sort(programs, new ProgramComparator()); // 先把所有项目(programs) 根据 结束时间谁早排序(new ProgramComparator())
        int result = 0;
        for (int i = 0; i < programs.length; i++) {
            if (timePoint <= programs[i].start) {//当前的时间点有没有小于会议的开始时间
                //有 result++
                result++;
                //然后把时间点移动到这个会议的结束时间
                timePoint = programs[i].end;
            }
        }
        return result;
    }
}
