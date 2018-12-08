package Exercises.Knapsack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PerfectSquareNumber {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                int num = j * j;
                int temp = dp[i - num] + 1;
                dp[i] = Math.min(dp[i], temp);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new PerfectSquareNumber().numSquares(12));
    }
}
