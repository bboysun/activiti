package com.darryl.activiti.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: Darryl
 * @Description: 蛇形走位输出二叉树
 *            7
 *        4       9
 *      2    5  8    10
 *
 *   我们需要蛇形输出：
 *   7
 *   9  4
 *   2   5   8   10
 * @Date: created in 2020/3/23 20:27
 */

public class SnakeTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        int[] arr = {4,2,5,9,8,10};
        for (int i=0; i<arr.length; i++) {
            buildTree(root, arr[i]);
        }

        List<LinkedList<Integer>> res = snakeTree(root);
        for (LinkedList<Integer> subList : res){
            for (Integer val : subList)
                System.out.print(" " + val + " ");
        }

    }


    // 蛇形走位输出
    public static List<LinkedList<Integer>> snakeTree(TreeNode root) {
        // 结果输出保存到这个res集合中
        List<LinkedList<Integer>> res = new ArrayList<>();
        // 保存每一层数据到这个集合中
        LinkedList<Integer> levelData = new LinkedList<>();
        // 弹出的数据缓存在一个list中，用null来对每一层数据进行分割
        LinkedList<TreeNode> cacheData = new LinkedList<>();

        // 先预先将root节点和null放入缓存list中
        cacheData.add(root);
        cacheData.add(null);

        // 判断遍历方向
        boolean isLeft = true;

        // 遍历这个缓存列表
        while (cacheData.size() > 0) {
            TreeNode curNode = cacheData.poll();
            if (curNode != null) {
                // 根据不同的方向将数据从头或者从尾插入到每一层数据列表中
                if (isLeft)
                    levelData.addLast(curNode.value);
                else
                    levelData.addFirst(curNode.value);

                if (curNode.left != null)
                    cacheData.addLast(curNode.left);
                if (curNode.right != null)
                    cacheData.addLast(curNode.right);
            } else {
                // 转变方向
                isLeft = !isLeft;
                // 当当前节点是null，其实表明我们已经完成了一层数据的遍历
                res.add(levelData);
                // 做一个分割
                if (cacheData.size() > 0)
                    cacheData.addLast(null);
                levelData = new LinkedList<>();
            }
        }
        return res;
    }

    // 构造一棵树
    public static void buildTree(TreeNode root, int value) {
        if (root.value < value) {
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

    // 构建一个叶子节点
    static class TreeNode{
        TreeNode left;
        TreeNode right;
        int value;
        public TreeNode(int value) {
            this.value = value;
        }
    }
}
