package Exercises.BuyAndSellStock;
/*
给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。

你可以无限次地完成交易，但是你每次交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。

返回获得利润的最大值。

示例 1:

输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
输出: 8
解释: 能够达到的最大利润:
在此处买入 prices[0] = 1
在此处卖出 prices[3] = 8
在此处买入 prices[4] = 4
在此处卖出 prices[5] = 9
总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
注意:

0 < prices.length <= 50000.
0 < prices[i] < 50000.
0 <= fee < 50000.

own[i] = Math.max(own[i-1], not_own[i-1] - price)
not_own[i] = Math.max(not_own[i-1], own[i-1] + price - fee)
 */
public class AnyTransactionsWithFee {
    public int maxProfit(int[] prices, int fee) {
        long own = Integer.MIN_VALUE;
        long not_own = 0;
        for (int price: prices) {
            long prev_own = own;
            own = Math.max(prev_own, not_own - price);
            not_own = Math.max(not_own, prev_own + price - fee);
        }
        return (int) not_own;
    }

    public static void main(String[] args) {
        System.out.println(new AnyTransactionsWithFee().maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }
}
