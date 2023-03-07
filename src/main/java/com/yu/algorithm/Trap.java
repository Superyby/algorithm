package com.yu.algorithm;

import java.util.Arrays;
import java.util.Stack;

/**
 * 接雨水  多解问题
 */
public class Trap {
    /**
     * 解法1  ： 按行求解
     * 整个思路就是，求第 i 层的水，遍历每个位置，如果当前的高度小于 i，并且两边有高度大于等于 i 的，说明这个地方
     * 定有水，水就可以加 1。
     */
    public static int byRow(int[] height) {
        int sum = 0;// 结果
        int max = getMax(height);// 找到最大高度  以便遍历
        for (int i = 0; i <= max; i++) {
            boolean flag = false; // 标记是否更新
            int temp = 0; // temp 变量  保存当前积累的水（因为可能下一个比当前位置低或高）
            for (int j = 0; j < height.length; j++) {
                if (flag && height[j] < i) {
                    temp++; // 积累
                }
                if (height[j] >= i) { // 找到下一堵"墙" 好  加到答案里
                    sum = sum + temp;
                    temp = 0;
                    flag = true;
                }
            }
        }
        return sum;
    }

    // 找到遍历的终止条件   最顶
    public static int getMax(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max) {
                max = height[i];
            }
        }
        return max;
    }

    /**
     * 解法2：按列求解
     */
    public static int byColumn(int[] height) {
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            // 找到左侧最大值
            // 这里  每次都遍历了一遍
            int maxLeft = 0;
            for (int j = i - 1; j > 0; j--) {
                if (height[j] > maxLeft) {
                    maxLeft = height[j];
                }
            }
            // 找到右侧最大值
            // 这里  每次都遍历了一遍
            int maxRight = 0;
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > maxRight) {
                    maxRight = height[j];
                }
            }

            int min = Math.min(maxLeft, maxRight);
            // 用小的一边  而且如果当前比最小值大的话 是存不住水的比如：
            /**
             *           |
             *       |   |
             *   |   |   |
             *
             *     中间的这个就存不住
             */
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }

    /**
     * 解法三：动态规划
     * 解法二中。对于每一列，我们求它左边最高的墙和右边最高的墙，都是重新遍历一遍所有高度，这里我们可以优化一下。
     * max_left [i] 代表第 i 列左边最高的墙的高度
     * max_right[i] 代表第 i 列右边最高的墙的高度。（一定要注意下，第 i 列左（右）边最高的墙，是不包括自身的，和 leetcode
     * 边的讲的有些不同）
     * <p color="blue">
     * 对于 max_left我们其实可以这样求。
     * <p color="blue">
     * max_left [i] = Max(max_left [i-1],height[i-1])。它前边的墙的左边的最高高度和它前边的墙的高度选一个较大的，就是当前列左边最高的墙了。
     * <p color="blue">
     * 对于 max_right我们可以这样求。
     * <p color="blue">
     * max_right[i] = Max(max_right[i+1],height[i+1]) 。它后边的墙的右边的最高高度和它后边的墙的高度选一个较大的，就是当前列右边最高的墙了。
     * <p>
     * 这样，我们再利用解法二的算法，就不用在 for 循环里每次重新遍历一次求 max_left 和 max_right 了。
     */
    public static int byDynamicPlanning(int[] height) {
        int sum = 0;
        // 创建两个数组替代之前的遍历方法 最后直接把两个数组的最大值进行比较
        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];

        for (int i = 1; i < height.length - 1; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i - 1]); // 直接让左边最大形成一个集合
        }
        for (int i = height.length - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], height[i + 1]); // maxRight[i + 1]表示i + 1后面的最大高度；height[i + 1]表示当前值后边那个值
        }
        for (int i = 1; i < height.length - 1; i++) { // 和解法二一样  注意看 这个循环是从左到右的
            int min = Math.min(maxLeft[i], maxRight[i]);
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }

    /**
     * 解法四：双指针
     * 动态规划中，我们常常可以对空间复杂度进行进一步的优化。
     * 可以看到，max_left [ i ] 和 max_right [ i ] 数组中的元素我们其实只用一次，然后就再也不会用到了。所以我们可以不用数组，只用一个元素就行了。
     * 我们不能同时把 max_right 的数组去掉，因为最后的 for 循环是从左到右遍历的，而 max_right 的更新是从右向左的。
     * 所以这里要用到两个指针，left 和 right，从两个方向去遍历。
     * <p color="blue">
     * height [ left - 1] 是可能成为 max_left 的变量， 同理，height [ right + 1 ] 是可能成为 right_max 的变量。
     * <p color="blue">
     * 只要保证 height [ left - 1 ] < height [ right + 1 ] ，那么 max_left 就一定小于 max_right。
     * <p color="blue">
     * 因为 max_left 是由 height [ left - 1] 更新过来的，而 height [ left - 1 ] 是小于 height [ right + 1] 的，而 height [ right + 1 ] 会更新 max_right，所以间接的得出 max_left 一定小于 max_right。
     * <p color="blue">
     * 反之，我们就从右到左更。
     */
    public static int byDoublePointer(int[] height) {
        int sum = 0;
        int maxLeft = 0;
        int maxRight = 0;
        int left = 1;
        int right = height.length - 2;
        for (int i = 1; i < height.length - 1; i++) {
            // 从左往右
            if (height[left - 1] < height[right + 1]) {
                maxLeft = Math.max(maxLeft, height[left - 1]);
                int min = maxLeft;
                if (min > height[left]) {
                    sum = sum + (min - height[left]);
                }
                left++; // 移动
            } else {
                maxRight = Math.max(maxRight, height[right + 1]);
                int min = maxRight;
                if (min > height[right]) {
                    sum = sum + (min - height[right]);
                }
                right--; // 移动
            }
        }
        return sum;
    }

    /**
     * 解法5：栈
     * 首先将 height [ 0 ] 入栈。然后 current 指向的高度大于栈顶高度，所以把栈顶 height [ 0 ] 出栈，然后栈空了，再把 height [ 1 ] 入栈。current 后移。
     * 后 current 指向的高度小于栈顶高度，height [ 2 ] 入栈，current 后移。
     * 然后 current 指向的高度大于栈顶高度，栈顶 height [ 2 ] 出栈。计算 height [ 3 ] 和新的栈顶之间的水。计算完之后继续判断 current 和新的栈顶的关系。
     * current 指向的高度大于栈顶高度，栈顶 height [ 1 ] 出栈，栈空。所以把 height [ 3 ] 入栈。currtent 后移。
     * 然后 current 指向的高度小于栈顶 height [ 3 ] 的高度，height [ 4 ] 入栈。current 后移。
     * 然后 current 指向的高度小于栈顶 height [ 4 ] 的高度，height [ 5 ] 入栈。current 后移。
     * 然后 current 指向的高度大于栈顶 height [ 5 ] 的高度，将栈顶 height [ 5 ] 出栈，然后计算 current 指向的墙和新栈顶 height [ 4 ] 之间的水。计算完之后继续判断current 的指向和新栈顶的
     * 关系。此时 height [ 6 ] 不大于栈顶 height [ 4 ] ，所以将 height [ 6 ] 入栈。current 后移。
     * 然后 current 指向的高度大于栈顶高度，将栈顶 height [ 6 ] 出栈。计算和新的栈顶 height [ 4 ] 组成两个边界中的水。然后判断 current 和新的栈顶 height [ 4 ] 的关系，依旧是大于，所以把
     * height [ 4 ] 出栈。计算 current 和 新的栈顶 height [ 3 ] 之间的水。然后判断 current 和新的栈顶 height [ 3 ] 的关系，依旧是大于，所以把 height [ 3 ] 出栈，栈空。将 current 指向的
     * height [ 7 ] 入栈。current 后移。
     * <p>
     * 其实不停的出栈，可以看做是在找与 7 匹配的墙，也就是 3 。
     * <p>
     * 而对于计算 current 指向墙和新的栈顶之间的水，根据图的关系，我们可以直接把这两个墙当做之前解法三的 max_left 和 max_right，然后之前弹出的栈顶当做每次遍历的 height [ i ]
     * 。水量就是 Min ( max _ left ，max _ right ) - height [ i ]，只不过这里需要乘上两个墙之间的距离。
     */
    public static int byStack(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0; // 当前值
        while (current < height.length) {
            // 如果栈不为空且当前指向的高度大于栈顶的高度就一直循环
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) { // peek() 看一下栈顶元素 不拿出来
                int h = height[stack.peek()]; // 取出栈顶元素
                stack.pop(); // 出栈
                if (stack.isEmpty()) { // 栈空就出去
                    break;
                }
                int distance = current - stack.peek() - 1; // 两堵墙之间的距离
                int min = Math.min(height[stack.peek()], height[current]);
                sum = sum + distance * (min - h);
            }
            stack.push(current); // 当前指向的墙入栈
            current++; // 指针后移
        }
        return sum;
    }


    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(byRow(height));
        System.out.println(byColumn(height));
        System.out.println(byDynamicPlanning(height));
        System.out.println(byDoublePointer(height));
        System.out.println(byStack(height));
        System.out.println('b' - 'a');
    }
}
