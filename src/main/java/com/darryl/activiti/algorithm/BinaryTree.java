package com.darryl.activiti.algorithm;

import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: Darryl
 * @Description: 实现一个二叉树的构建
 *           4
 *       2        10
 *     1   3    6     12
 * @Date: created in 2020/3/20 20:45
 */

public class BinaryTree {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        int[] arr = {2,1,3,10,6,12};
        for (int i=0; i<arr.length; i++) {
            buildTree(root, arr[i]);
        }
        List<Integer> res = Lists.newArrayList();
        //showTreeBefore(res, root);
        //showTreeAfter(res, root);
        showTreeMiddle(res, root);

        for (Integer val : res)
            System.out.print(" " + val + " ");
        System.out.println();
        System.out.println("==============");

        List<LinkedList<Integer>> snakeTree = snakeTree(root);

        for (LinkedList<Integer> sub : snakeTree) {
            for (Integer val : sub) {
                System.out.print(" " + val + " ");
            }
        }

    }

    // 蛇形走位打印一棵二叉树
    private static List<LinkedList<Integer>> snakeTree(TreeNode root) {
        List<LinkedList<Integer>> res = Lists.newArrayList();

        // 存储每一层节点数据
        LinkedList<Integer> subRes = Lists.newLinkedList();

        // 用来缓存节点
        LinkedList<TreeNode> linkedList = Lists.newLinkedList();
        linkedList.addLast(root);
        // 以null为分隔符
        linkedList.addLast(null);
        // 用来控制顺序
        boolean fromLeft = true;

        while (linkedList.size() > 0)  {
            TreeNode curNode = linkedList.pollFirst();
            if (curNode != null) {
                if (fromLeft)
                    subRes.addLast(curNode.value);
                else
                    subRes.addFirst(curNode.value);

                if (curNode.left != null)
                    linkedList.addLast(curNode.left);
                if (curNode.right != null)
                    linkedList.addLast(curNode.right);
            } else {
                fromLeft = !fromLeft;
                if (subRes.size() > 0)
                    linkedList.addLast(null);
                res.add(subRes);
                subRes = Lists.newLinkedList();
            }
        }

        return res;
    }


    // 前序遍历：根节点--左节点--右节点，输出一棵树
    private static void showTreeBefore(List<Integer> nodeList, TreeNode root) {
        if (root == null)
            return;
        nodeList.add(root.value);
        if (root.left != null)
            showTreeBefore(nodeList, root.left);
        if (root.right != null)
            showTreeBefore(nodeList, root.right);
    }

    // 后序遍历：左--右--根，输出一棵树
    private static void showTreeAfter(List<Integer> nodeList, TreeNode root) {
        if (root != null) {
            if (root.left != null)
                showTreeAfter(nodeList, root.left);
            if (root.right != null)
                showTreeAfter(nodeList, root.right);
            nodeList.add(root.value);
        }
    }

    // 中序遍历：左-根-右，输出一棵树
    private static void showTreeMiddle(List<Integer> nodeList, TreeNode root) {
        if (root != null) {
            if (root.left != null)
                showTreeMiddle(nodeList, root.left);
            nodeList.add(root.value);
            if (root.right != null)
                showTreeMiddle(nodeList, root.right);
        }
    }

    // 构建一棵树
    private static void buildTree(TreeNode root, int value) {
        if (value > root.value) {
            if (root.right == null)
                root.right = new TreeNode(value);
            else
                buildTree(root.right, value);
        } else {
            if (root.left == null)
                root.left = new TreeNode(value);
            else
                buildTree(root.left, value);
        }
    }

    // 构建一个树的节点
    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int value;

        public TreeNode(int value) {
            this.value = value;
        }
    }
}
