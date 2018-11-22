package Array;

import java.util.ArrayList;
import java.util.List;

/*
***********     Boyer-Moore Majority Vote algorithm    ***********
给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在众数。

示例 1:

输入: [3,2,3]
输出: 3
示例 2:

输入: [2,2,1,1,1,2,2]
输出: 2
 */
public class MajorityNumber {
    // 只可能最多有一个数超过 [n/2]
    public int majorityElement(int[] nums) {      // 遇到相同的数count++，遇到不同的数count--，count为0时换个数开始计数
        int ans = 0;
        int count = 0;
        for (int n: nums) {
            if (count == 0) ans = n;
            if (ans == n) count++;
            else count--;
        }
        return ans;
    }

/*
给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。

说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。

示例 1:

输入: [3,2,3]
输出: [3]
示例 2:

输入: [1,1,1,3,3,2,2,2]
输出: [1,2]
 */
    // 只可能最多有两个数超过 [n/3]
    public List<Integer> majorityNumber2(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums.length == 0) return ans;
        int candidate1 = nums[0], candidate2 = nums[0];
        int count1 = 0, count2 = 0;
        for (int n: nums) {
            if (candidate1 == n) {
                count1++;
            } else if (candidate2 == n) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = n;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = n;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        // verify
        count1 = 0;
        count2 = 0;
        for (int n: nums) {
            if (n == candidate1) count1++;
            else if (n == candidate2) count2++;
        }
        if (count1 > nums.length / 3) ans.add(candidate1);
        if (count2 > nums.length / 3) ans.add(candidate2);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new MajorityNumber().majorityNumber2(new int[]{1,1,1,3,3,2,2,2}));
    }
}
