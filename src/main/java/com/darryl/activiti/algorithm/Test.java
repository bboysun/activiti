package com.darryl.activiti.algorithm;
import java.util.Scanner;

/**
 * 给定一个字符串chas[],其中只含有字母字符和“*”字符，现在想把所有“*”全部挪到chas的左边，字母字符移到chas的右边。完成调整函数。要求时间复杂度为O(N)，空间复杂度为O(1)。
 * 举例：输入"12**345"，输出"**12345"。
 */
public class Test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder(sc.nextLine());
        int index = sb.length() - 1;
        for (int i=sb.length()-1;i>=0;i--) {
            if (sb.charAt(i) != '*') {
                sb.setCharAt(index,sb.charAt(i));
                index--;
            }
        }
        for (index=index;index>=0;index--) {
            sb.setCharAt(index,'*');
        }
        System.out.println(sb.toString());
    }
}
