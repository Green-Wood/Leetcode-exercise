package DP.Knapsack;

/*
给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。

示例 1:

输入: coins = [1, 2, 5], amount = 11
输出: 3
解释: 11 = 5 + 5 + 1
示例 2:

输入: coins = [2], amount = 3
输出: -1
说明:
你可以认为每种硬币的数量是无限的。


HINT：背包问题，类型1，达到某个点的最小花费
 */

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        return change1(coins, amount, new int[amount]);
    }

    private int change1(int[] coins, int rem, int[] count){
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin: coins){
            int res = change1(coins, rem - coin, count);
            if (res >= 0 && res < min){
                min = res + 1;
            }
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    private int change2(int[] coins, int amount){
        int[] dp = new int[amount+1];
        int sum = 0;
        while (++sum <= amount){      // 填写dp数组之中的内容，0～amount
            int min = -1;
            for (int coin: coins){             // 对每一种硬币进行尝试，找到次数最少的那个次数，存入min
                if (coin <= sum && dp[sum-coin] != -1){
                    int temp = dp[sum-coin] + 1;
                    min = min < 0 ? temp : Math.min(min, temp);
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args){
        System.out.println(new CoinChange().change2(new int[]{2}, 3));
    }
}
