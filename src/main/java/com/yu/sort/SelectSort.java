package com.yu.sort;

import java.util.Arrays;

public class SelectSort {
    //选择排序 ： 对于长度为N的数组 选择排序需要大约 N^2 / 2次比较  和N次交换
    //特点： 运行时间和输入无关；数据移动是最少的
    public static void main(String[] args) {
        int[] arr = {1, 8, 5, 9, 2, 6};
        System.out.println(Arrays.toString(selectionSort(arr)));
        System.out.println(Arrays.toString(sort(arr)));
    }

    public static int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {    //选出之后待排序中值最小的位置
                if (arr[j] < arr[min]) {
                    min = j;
                }
            } //找完一轮  换那个最小值到头
            if (min != i) {
                int temp = arr[min];      //交换操作
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
        return arr;
    }

    public static int[] sort(int[] arr) {
        int alen = arr.length;
        for (int i = 0; i < alen - 1; i++) {
            int min = i;  //最小值索引
            for (int j = i + 1; j < alen; j++) { //从i的下一个元素比较
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
        return arr;
    }


//
//    public static void sort(Comparable[] a) {
//        //将a[]按照升序排列
//        int alen = a.length;//a[] 的长度
//        for (int i = 0; i < alen; i++) {
//            //选择排序 将a[i]和a[i+1...n]中最小的元素交换
//            int min = i;  //最小元素索引
//            for (int j = 0; j < alen; j++) {
//                if (less(a[j], a[min])) {
//                    exch(a, i, min);
//                }
//            }
//        }
//    }
//
//    public static boolean less(Comparable v, Comparable w) {
//        //比较
//        return v.compareTo(w) < 0;
//    }
//
//    public static void exch(Comparable[] a, int i, int j) {
//        //交换
//        Comparable t = a[i];
//        a[i] = a[j];
//        a[j] = t;
//    }
}
