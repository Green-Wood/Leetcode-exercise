package Exercises.LongestSubsequence;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
给出一个由无重复的正整数组成的集合，找出其中最大的整除子集，子集中任意一对 (Si，Sj) 都要满足：Si % Sj = 0 或 Sj % Si = 0。

如果有多个目标子集，返回其中任何一个均可。



示例 1:

输入: [1,2,3]
输出: [1,2] (当然, [1,3] 也正确)
示例 2:

输入: [1,2,4,8]
输出: [1,2,4,8]

HINT: 类似最长递增子序列，LIS

1、dp求得最长的子集
2、从后往前找子集中的元素
 */
public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int maxIndex = 0;
        // LIS
        for (int i = 0; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] > dp[maxIndex]) maxIndex = i;
        }
        List<Integer> ans = new ArrayList<>();
        int currDP = dp[maxIndex];
        for (int i = maxIndex; i >= 0; i--) {
            if (nums[maxIndex] % nums[i] == 0 && dp[i] == currDP) {
                ans.add(nums[i]);
                currDP--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LargestDivisibleSubset().largestDivisibleSubset(new int[]{1, 2, 3, 4}));
    }
}
