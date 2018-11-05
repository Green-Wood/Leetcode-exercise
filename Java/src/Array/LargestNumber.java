package Array;

import java.util.Arrays;
/*
给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。

示例 1:

输入: [10,2]
输出: 210
示例 2:

输入: [3,30,34,5,9]
输出: 9534330
说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。

HINT: 使用排序
 */
public class LargestNumber {
    public String largestNumber(int[] nums){
        if (nums == null || nums.length == 0) return "";
        String[] s_num = new String[nums.length];
        for (int i = 0; i < nums.length; i++){
            s_num[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(s_num, (s1, s2) -> (s2 + s1).compareTo(s1 + s2)); // **********

        if (s_num[0].charAt(0) == '0') return "0";
        StringBuilder sb = new StringBuilder();
        for (String num: s_num){
            sb.append(num);
        }
        return sb.toString();
    }
    public static void main(String[] args){
        System.out.println(new LargestNumber().largestNumber(new int[]{3, 30, 34, 5, 9}));
    }
}
