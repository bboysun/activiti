package com.darryl.activiti.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Darryl
 * @Description: 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 * 输入: [3,4,7,1]
 * 输出: 2
 * @Date: created in 2020/3/17 21:19
 */

public class FindMinNum {

    public static void main(String[] args) {
        int[] arr = {1000,-1};
        System.out.println(findMinNum(arr));
    }

    private static int findMinNum(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        Arrays.sort(nums);
        int i = 0;
        for (i=0; i<nums.length-1; i++) {
            if (nums[i] > 1)
                return 1;
            if (nums[i+1] - nums[i] > 1 && nums[i] > 0)
                break;
        }
        if (nums[i] < 0)
            return 1;
        else
            return nums[i] + 1;
    }

}
