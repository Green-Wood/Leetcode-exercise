package DP;
/*
给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。

示例 1:

输入: [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。
示例 2:

输入: [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。

HINT：与maxSubSum类似
 */
public class maxSubProduct {
    public int maxProduct(int[] nums) {
        int maxProduct = nums[0];
        int currMax = maxProduct;
        int currMin = maxProduct;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {                         // swap(max, min)
                int temp = currMax;
                currMax = currMin;
                currMin = temp;
            }

            currMax = Math.max(currMax * nums[i], nums[i]);
            currMin = Math.min(currMin * nums[i], nums[i]);

            maxProduct = Math.max(currMax, maxProduct);
        }
        return maxProduct;
    }

    public static void main(String[] args) {
        System.out.println(new maxSubProduct().maxProduct(new int[]{2, 3, -2, 4}));
    }
}
