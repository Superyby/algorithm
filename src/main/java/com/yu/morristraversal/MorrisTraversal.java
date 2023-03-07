package com.yu.morristraversal;

/**
 * Morris遍历<br></br>
 * 一种遍历二叉树的方式，并且时间复杂度O(N) ，额外空间复杂度O(1)<br></br>
 * 通过利用原树中大量空闲指针的方式，达到节省空间的目的
 * <br></br>
 * Morris遍历细节
 * <br></br>
 * 假设来到当前节点cur，开始时cur来到头节点的位置<br></br>
 * 1. 如果cur没有左孩子，cur向右移动(cur = cur.right)
 * <br></br>
 * 2. 如果cur有左孩子，找到左子树上最右的节点mostRight:
 * <br></br>------a. 如果mostRight的右指针指向空，让其指向cur，然后cur向左移动(cur = cur.left)<br></br> ------ b. 如果mostRight的右指针指向cur，让其指向null，然后cur向右移动(cur = cur.right)<br></br>
 * 3. cur为空时遍历停止
 * <br></br>
 * 有左树的节点会遍历两次
 */
public class MorrisTraversal {
    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //递归版本的遍历
    public static void process(Node head) {
        if (head == null) {
            return;
        }
        //1 (先序)
        process(head.left);
        //2 (中序)
        process(head.right);
        //3 (后序)
    }

    //Morris遍历
    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {// cur != null 就一直遍历
            mostRight = cur.left; //cur先把自己的left给mostRight
            if (mostRight == null) { //如果cur没有左孩子  那mostRight就是空的
                while (mostRight.right != null && mostRight.right != cur) {//两个条件
                    //两个条件是因为  当前节点的左子树的最右节点的右指针可以是两个状态
                    mostRight = mostRight.right;
                }
                //跑完上面的while mostRight就变成了cur左子树上，最右的节点了
                if (mostRight.right == null) {//这是第一次来到cur
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;//continue到最上边那个while  (大过程周而复始)
                } else { // 否则 mostRight.right == cur  第二次来到cur
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }

    //Morris先序遍历
    //只一次 直接打印  出现两次 只打印第一次
    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {//有左子树
                while (mostRight.right != null && mostRight != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {//这是第一次来到cur
                    System.out.println(cur.value);
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { //第二次来到这个节点
                    mostRight.right = null;
                }
            } else { //没有左子树情况
                System.out.println(cur.value);
            }
            cur = cur.right;
        }
    }

    //Morris中序
    // 只一次 直接打印   两次  打印第二次
    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            System.out.println(cur.value);
            cur = cur.right;
        }
    }

    //Morris后序遍历  逆序打印左树右边界  最后单独打印整棵树的右边界(逆序)

    public static void morrisPos(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);
        System.out.println();
    }

    //小函数  ： 以X为头的树，逆序打印这棵树的右边界
    public static void printEdge(Node X) {
        Node tail = reverseEdge(X);
        Node cur = tail;
        while (cur != null) {
            System.out.println(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    public static Node reverseEdge(Node from) {
        Node pre = null;
        Node next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }
}
