package com.darryl.activiti.jvm_class_load;

/**
 * @Auther: Darryl
 * @Description: 树 之重写hashcode 也没啥意义
 * @Date: created in 2020/3/16 23:53
 */

public class Tree {
    private int high;

    @Override
    public int hashCode() {
        return high*31%7;
    }

    public Tree(int high) {
        this.high = high;
    }

    public static void main(String[] args) {
        Tree tree1 = new Tree(2);
        Tree tree2 = new Tree(2);
        System.out.println(tree1.equals(tree2));
        System.out.println(tree1 == tree2);
        System.out.println("tree1 hashcode:" + tree1.hashCode());
        System.out.println("tree2 hashcode:" + tree2.hashCode());
    }
}
