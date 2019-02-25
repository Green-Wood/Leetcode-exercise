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

全体可用公式
own[k, i] = max(own[k, i-1], not_own[k-1, i-1] - price)
not_own[k, i] = max(not_own[k, i-1], own[k, i-1] + price)

 */
public class KTransactions {
    public int maxProfit(int k, int[] prices) {
        if (k > prices.length / 2) return quickSolve(prices);        // 每天都可以交易，避免爆内存

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



    // 从1到3对空间进行简化
    // 1   O(2N^2)
    public int general(int k, int[] prices) {
        if (prices.length == 0) return 0;
        if (k > prices.length / 2) return quickSolve(prices);        // 每天都可以交易

        int[][] own = new int[k + 1][prices.length];
        int[][] not_own = new int[k + 1][prices.length];

        for (int t = 1; t <= k; t++) {
            own[t][0] = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                own[t][i] = Math.max(own[t][i-1], not_own[t-1][i-1] - prices[i]);
                not_own[t][i] = Math.max(not_own[t][i-1], own[t][i-1] + prices[i]);
            }
        }

        return not_own[k][prices.length-1];
    }

    // O(N^2)
    public int general2(int k, int[] prices) {
        if (prices.length == 0) return 0;
        if (k > prices.length / 2) return quickSolve(prices);        // 每天都可以交易

        int[][] not_own = new int[k + 1][prices.length];
        for (int t = 1; t <= k; t++) {
            int own = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                int prev_own = own;
                own = Math.max(prev_own, not_own[t-1][i-1] - prices[i]);
                not_own[t][i] = Math.max(not_own[t][i-1], prev_own + prices[i]);
            }
        }
        return not_own[k][prices.length - 1];
    }

    // O(2N)
    public int general3(int k, int[] prices) {
        if (prices.length == 0) return 0;
        if (k > prices.length / 2) return quickSolve(prices);

        int[] prev_not_own = new int[prices.length];
        int[] not_own = new int[prices.length];

        for (int t = 1; t <= k; t++) {
            int own = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                int prev_own = own;
                own = Math.max(prev_own, prev_not_own[i-1] - prices[i]);
                not_own[i] = Math.max(not_own[i-1], prev_own + prices[i]);
            }
            System.arraycopy(not_own, 0, prev_not_own, 0, prices.length);
        }

        return not_own[prices.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(new KTransactions().general3(2, new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
    }
}
