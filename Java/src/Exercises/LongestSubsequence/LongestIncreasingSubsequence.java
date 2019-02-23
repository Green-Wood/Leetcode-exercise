package Exercises.LongestSubsequence;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
    // O(n^2)
    public int LIS_Squre(int[] nums){
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int result = 1;
        for (int i = 1; i < nums.length; i++){
            for (int j = 0; j < i; j++){
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
    // O(nlogn)
    /* Note: dp array does not result in longest increasing subsequence,
     but length of dp array will give you length of LIS.*/
    public int LIS_NlogN(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int n: nums) {
            int i = Arrays.binarySearch(dp, 0, len, n);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = n;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    public static void main(String[] args){
        int[] a = {1, 3, 2, 5, 4, 10};
        Arrays.sort(a);
        System.out.println(Arrays.binarySearch(a, 5));
    }
}
