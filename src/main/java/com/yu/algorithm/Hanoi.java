package com.yu.algorithm;

/**
 * 汉诺塔问题
 * 打印n层汉诺塔从最左边移动到最右边的全部过程
 * 步骤:
 * Move 1 from 左 to 中 -
 * Move 2 from 左 to 右 -  这三步是想办法把1~i-1 移动到最右边
 * Move 1 from 中 to 右 -
 * Move 3 from 左 to 中  :将最下边的那个(i)移动到中间
 * Move 1 from 右 to 左  :把最右边的1移动到最左边
 * Move 2 from 右 to 中  :把左右边的2移动到中间(3的上边)
 * Move 1 from 左 to 中  :最后把1移动到中间(2的上边)
 */
public class Hanoi {
    public static void hanoi(int n) {
        if (n > 0) {
            func(n, "左", "中", "右");//对于所有目标来说  它的出发地是左 要去右 中是另外一个
        }
    }

    public static void func(int i, String start, String end, String other) {
        if (i == 1) {//为1 那就直接移动 想怎么动怎么动
            System.out.println("Move 1 from " + start + " to " + end);
        } else {
            func(i - 1, start, other, end); // i-1 先从start 挪动到 other end变成另外一个
            System.out.println("Move " + i + " from " + start + " to " + end);
            func(i - 1, other, end, start); // 1~i-1目标从other挪回来 start变成另外一个
        }
    }

    public static void main(String[] args) {
        int n = 3;
        hanoi(3);
    }
}
