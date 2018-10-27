package DP;

import java.util.Arrays;

public class LongestInceeasingSubsequence {
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

    public static void main(String[] args){
        int[] a = {1, 3, 2, 5, 4, 10};
        Arrays.sort(a);
        System.out.println(Arrays.binarySearch(a, 5));
    }
}
