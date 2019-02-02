package Exercises.BuyAndSellStock;
/*
给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。

注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:

输入: [3,3,5,0,0,3,1,4]
输出: 6
解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
示例 2:

输入: [1,2,3,4,5]
输出: 4
解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
示例 3:

输入: [7,6,4,3,1]
输出: 0
解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。


HINT:
First assume that we have no money, so buy1 means that we have to borrow money from others, we want to borrow less
so that we have to make our balance as max as we can(because this is negative).

sell1 means we decide to sell the stock, after selling it we have price[i] money and we have to give back the money we
owed, so we have price[i] - |buy1| = prices[i ] + buy1, we want to make this max.

buy2 means we want to buy another stock, we already have sell1 money, so after buying stock2 we have buy2 =
sell1 - price[i] money left, we want more money left, so we make it max

sell2 means we want to sell stock2, we can have price[i] money after selling it, and we have buy2 money left before,
so sell2 = buy2 + prices[i], we make this max.

So sell2 is the most money we can have.
 */
public class TwoTransActions {
    public int maxProfit(int[] prices) {
        int sell1 = 0, sell2 = 0;
        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
        for (int p: prices) {
            buy1 = Math.max(buy1, -p);
            sell1 = Math.max(sell1, buy1 + p);
            buy2 = Math.max(buy2, sell1 - p);
            sell2 = Math.max(sell2, buy2 + p);
        }
        return sell2;
    }
}
