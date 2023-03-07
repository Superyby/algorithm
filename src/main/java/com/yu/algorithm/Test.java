package com.yu.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        int[][] a = {{0, 1}, {2, 3}};
        List<int[]> merged = new ArrayList<int[]>();
        merged.add(0, new int[]{1, 2});
        System.out.println(merged.get(merged.size() - 1)[0]);
        System.out.println(merged.get(merged.size() - 1)[1]);
    }
}
