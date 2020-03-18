package com.darryl.activiti.algorithm;

/**
 * @Auther: Darryl
 * @Description: 小明去一排糖果屋买糖，他不能从相邻的糖果屋买糖，求他能买到最多的糖
 * [2,4,1,6,4] 糖果屋的糖
 * 返回最多的糖数：10
 * @Date: created in 2020/3/17 20:23
 */

public class Candy {

    public static void main(String[] args) {
        int[] candyHome = {2,4,1,6,3,3};
        System.out.println(solution(candyHome));
    }

    private static int solution(int[] arr) {
        if (arr.length == 0)
            return 0;
        if (arr.length == 1)
            return arr[0];
        if (arr.length == 2)
            return arr[0]>arr[1] ? arr[1] : arr[0];


        int[] max = new int[arr.length + 1];
        max[1] = arr[0];
        max[2] = arr[0]>arr[1] ? arr[0] : arr[1];
        // max[n] 表示n个糖果屋最大的糖果数，
        // 那么max[n] = max(arr[n-1] + max[n-2], arr[n-2] + max[n-3])
        for (int i=3; i<=arr.length; i++) {
            max[i] = arr[i-1] + max[i-2] > arr[i-2] + max[i-3] ? arr[i-1] + max[i-2] : arr[i-2] + max[i-3];
        }
        // 返回n个糖果屋的最大糖果数
        return max[arr.length];
    }
}
