package Exercises.BuyAndSellStock;
/*
给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。

注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:

输入: [2,4,1], k = 2
输出: 2
解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
示例 2:

输入: [3,2,6,5,0,3], k = 2
输出: 7
解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。


HINT: General idea id dp
dp[k, i] 表示若进行k次交易，在i天能够得到的最大利润。
dp[k, i] = max(dp[k, i - 1], prices[i] - prices[j] + dp[k - 1, j])   j belong to [0, i - 1]

OneTransaction TwoTransaction都可以由dp的循环化简而来

 */
public class KTransactions {
    public int maxProfit(int k, int[] prices) {
        if (k > prices.length / 2) return quickSolve(prices);

        int[][] dp = new int[k + 1][prices.length];
        for (int t = 1; t <= k; t++) {
            int localMax = -prices[0];                     // localMax = Max(dp[k - 1, j], prices[j])
            for (int i = 1; i < prices.length; i++) {
                dp[t][i] = Math.max(dp[t][i - 1], prices[i] + localMax);
                localMax = Math.max(localMax, dp[t - 1][i] - prices[i]);
            }
        }
        return dp[k][prices.length - 1];
    }

    private int quickSolve(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }
}
