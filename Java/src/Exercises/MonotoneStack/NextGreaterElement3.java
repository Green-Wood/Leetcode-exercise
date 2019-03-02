package Exercises.MonotoneStack;

import java.util.Arrays;

/*
给定一个32位正整数 n，你需要找到最小的32位整数，其与 n 中存在的位数完全相同，并且其值大于n。如果不存在这样的32位整数，则返回-1。

示例 1:

输入: 12
输出: 21
示例 2:

输入: 21
输出: -1

1、从右往左找到逆序数
2、向右寻找大于逆序数的最小数
3、交换
4、使逆序数之后的数升序排序

类似于NextPermutation
 */
public class NextGreaterElement3 {
    public int nextGreaterElement(int n) {
        char[] nums = Integer.toString(n).toCharArray();
        int index = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                index = i;
                break;
            }
        }
        if (index == -1) return -1;
        int minMaxIndex = index + 1;
        for (int i = index + 2; i < nums.length; i++) {
            if (nums[i] > nums[index] && nums[i] < nums[minMaxIndex]) {
                minMaxIndex = i;
            }
        }
        char temp = nums[index];
        nums[index] = nums[minMaxIndex];
        nums[minMaxIndex] = temp;

        Arrays.sort(nums, index + 1, nums.length);

        long val = Long.parseLong(String.valueOf(nums));

        return val > Integer.MAX_VALUE ? -1 : (int) val;
    }

    public static void main(String[] args) {
        System.out.println(new NextGreaterElement3().nextGreaterElement(123));
    }
}
