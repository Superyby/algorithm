package com.yu.algorithm;

import java.util.Stack;

/**
 * 逆序打印一个栈
 */
public class ReverseStack {

    public static void reverse(Stack<Integer> stack){
        if (stack.isEmpty()){
            return;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    /**
     * 移除栈底元素并返回
     * @param stack
     * @return
     */
    public static int f(Stack<Integer> stack){
        int result = stack.pop();
        if (stack.isEmpty()){
            return result;
        } else {
            int last = f(stack);//递归调用
            stack.push(result);//把当前值压回栈
            return last;//last往上返回
        }
    }
}
