package com.yu.hash;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * bit类型数组
 */
public class BitMap {
    public static void main(String[] args) {
        int a = 0;
        // a 32bit
        int[] arr = new int[10];//  32bit * 10 -> 320bits
        //arr[0]  int 0 ~ 31
        //arr[1]  int 32 ~ 63
        //arr[2]  int 64 ~ 95

        int i = 178; //想取得178个bit的状态

        int numIndex = i / 32;//定位178应该在哪个数上找
        int bitIndex = i % 32;//这个数上一共有32位  应该找那一位(178)

        //拿到ide 状态
        int s = ((arr[numIndex] >> (bitIndex)) & 1);

        //把i状态改成1
        arr[numIndex] = arr[numIndex] | (1 << (bitIndex));

        //把i状态改成0
        arr[numIndex] = arr[numIndex] & (~ (1 << bitIndex));

        //把178位的状态拿出来
        i = 178;
        //bit 0 1
        int bit = (arr[i / 32] >> (i % 32)) & 1;


        // 测试
//        Hashtable
//        HashMap
    }
}
