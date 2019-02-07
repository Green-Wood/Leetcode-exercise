package DP;
/*
给定一个非负整数数组，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

你的目标是使用最少的跳跃次数到达数组的最后一个位置。

示例:

输入: [2,3,1,1,4]
输出: 2
解释: 跳到最后一个位置的最小跳跃数是 2。
     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
说明:

假设你总是可以到达数组的最后一个位置。
 */
public class JumpGame2 {
    public int jump(int[] nums) {
        int currEnd = 0;                // 当前步数下能走到最远的距离
        int maxRange = 0;              //  下一步能走到的最远距离
        int currStep = 0;             // 当前步数
        for (int i = 0; i < nums.length - 1; i++) {
            maxRange = Math.max(maxRange, nums[i] + i);
            if (i == currEnd) {
                currStep++;
                currEnd = maxRange;
            }
        }
        return currStep;
    }

    public static void main(String[] args) {
        System.out.println(new JumpGame2().jump(new int[]{2, 3, 1, 0, 4}));
    }
}
