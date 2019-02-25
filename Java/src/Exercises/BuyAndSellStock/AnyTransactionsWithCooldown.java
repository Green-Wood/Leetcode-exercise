package Exercises.BuyAndSellStock;
/*
给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​

设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
示例:

输入: [1,2,3,0,2]
输出: 3
解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]

HINT:  我们有三个状态：buy, sell, rest。因此我们需要做的是在第i天决定我们选择哪一种状态
1、我们可以在任何时候选择rest
2、只有我们在i-1天仍持有股票时，我们才可以sell
3、只有我们在i-2天未持有股票时，我们才可以buy(因为有一天rest)
可以看出，我们是否sell或者buy是由第i-1天是否持有股票来决定的
令own[i]为前i天能够得到的最大利润，且在第i天仍持有股票
  not_own[i]为前i天能够得到的最大利润，且在第i天未持有股票

状态转移可以写作
own[i] = max(own[i-1], not_own[i-2] - price)
not_own[i] = max(own[i-1] + price, not_own[i-1])
 */
public class AnyTransactionsWithCooldown {
    public int maxProfit(int[] prices) {
        int own = Integer.MIN_VALUE, prev_not_won = 0, not_own = 0;
        for (int price: prices) {
            int prev_own = own;
            own = Math.max(prev_own, prev_not_won - price);
            prev_not_won = not_own;
            not_own = Math.max(prev_own + price, prev_not_won);
        }
        return not_own;
    }
}
