package com.yu.treedp;

/**
 * 树形dp套路
 * <p></p>
 * 树形dp套路使用的前提是：
 * 如果题目求解目标是S规则，则求解流程可以定成以每一节点为头节点的子树在S规则下的每一个答案，并且最终答案一定在其中。<br></br>
 *<br></br>
 * 树形dp套路第一步：<br></br>
 * 以某个节点X为头节点的子树中，分析答案有哪些可能性，并且这种分析是以X的左子树，X的右子树，和X整棵树的角度来考虑的<br></br>
 * <br></br>
 * 树形dp第二步：<br></br>
 * 根据第一步的可能性分析，列出所有需要的信息<br></br>
 * 树形dp套路第三步<br></br>
 *合并第二步的信息，对左树和右树提出同样的要求，并写出信息结构<br></br>
 * <br></br>
 * 树形dp套路第四步：<br></br>
 * 设计递归函数，递归函数是处理以X为头节点的情况下的答案<br></br>
 * 包括设计递归的basecase，默认直接得到左树和右树的所有信息，以及把可能性做整合，并且要返回第三步信息结构这四个小步骤
 */
public class MaxDistanceInTree {

    /**
     * 二叉树间的最大距离问题
     * 分为 根节点 x   参与 与 不参与 讨论
     * <p></p>
     * 从二叉树的节点a出发，可以向上或向下走，但沿途的节点只能经过一次，到达节点b时路径上的节点叫a到b的距离，那么二叉树任何两个节点之间都有距离，求整棵树上最大距离
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }


    public static int maxDistance(Node head) {
        return process(head).maxDistance;
    }

    public static class Info {
        public int maxDistance;
        public int height;

        public Info(int dis, int h) {
            maxDistance = dis;
            height = h;
        }
    }


    //返回以x为头的整棵树 两个信息
    public static Info process(Node x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        //三种可能性
        // 1
        int p1 = leftInfo.maxDistance;
        // 2
        int p2 = rightInfo.maxDistance;
        // 3
        int p3 = leftInfo.height + 1 + rightInfo.height;
        // 总
        int maxDistance = Math.max(p3, Math.max(p1, p2));
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(maxDistance, height);
    }
}
