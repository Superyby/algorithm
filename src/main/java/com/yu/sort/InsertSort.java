package com.yu.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {1, 8, 5, 9, 2, 6};
        System.out.println(Arrays.toString(sort(arr)));
    }
    public static int[] sort(int[] arr){
        int alen = arr.length;
        for (int i = 1; i <alen; i++) {
            int temp = arr[i];
            for (int j = i; j >= 0; j--) {
                if (j > 0 && arr[j-1] > temp){
                    arr[j] = arr[j-1];
                }else {
                    arr[j] = temp;
                    break;
                }
            }
        }
        return arr;
    }
}
