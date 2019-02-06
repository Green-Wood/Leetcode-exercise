package DP;
/*
你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

示例 1:

输入: [2,3,2]
输出: 3
解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
示例 2:

输入: [1,2,3,1]
输出: 4
解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     偷窃到的最高金额 = 1 + 3 = 4 。


考虑将问题分解，如果我们不抢i号房间，那么我们可以将这个环从i号房间打开。就可以用HouseRob1中的方法进行解决
1、若不抢第一个房间，则[1, n]的房间可以变成HouseRob1中的问题
2、若抢第一个房间，则[2, n-1]房间变成HouseRob1中的问题

 */
public class HouseRobber2 {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        int notRobFirst = rangeRob(nums, 1, nums.length - 1);
        int robFirst = nums[0] + rangeRob(nums, 2, nums.length - 2);
        return Math.max(notRobFirst, robFirst);
    }

    private int rangeRob(int[] nums, int lo, int hi) {
        int prev_i_1 = 0, prev_i_2  = 0;
        for (int i = lo; i <= hi; i++) {
            int curr = Math.max(prev_i_2 + nums[i], prev_i_1);
            prev_i_2 = prev_i_1;
            prev_i_1 = curr;
        }
        return prev_i_1;
    }
}
