package com.yu.algorithm;

/**
 * 苹果装袋子  有两种类型袋子  6,8  并且只能6,8  求最优解
 */
public class AppleMinBags {
    public static int minBags(int apple) {
        if (apple < 0) {
            return -1;
        }
        int bag6 = -1;//一上来还没分配
        int bag8 = apple / 8;
        int rest = apple - bag8 * 8; //减去8袋子还剩多少苹果
        while (bag8 >= 0 && rest < 24) { //剩余苹果数不必大于24
            int restUse6 = minBagBase6(rest);
            if (restUse6 != -1) {
                bag6 = restUse6;
                break;
            }
            rest = apple - 8 * (--bag8);
        }
        return bag6 == -1 ? -1 : bag6 + bag8;
    }

    public static int minBagBase6(int rest) {
        return rest % 6 == 0 ? (rest / 6) : -1;
    }

    public static int minBagAwesome(int apple) {
        if ((apple & 1) != 0) { //如果是奇数 返回-1
            return -1;
        }
        if (apple < 18) {
            return apple == 0 ? 0 : (apple == 6 || apple == 8) ? 1
                    : (apple == 12 || apple == 14 || apple == 16) ? 2 : -1;
        }
        return (apple - 18) / 8 + 3;
    }
}
