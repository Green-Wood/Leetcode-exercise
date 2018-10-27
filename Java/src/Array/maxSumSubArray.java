package Array;

/*
给定（可能有负数）整数序列A1, A2, A3..., An， 求这个序列中子序列和的最大值。
（为方便起见，如果所有整数均为负数，则最大子序列和为0）。
例如：输入整数序列： -2, 11, 8, -4, -1, 16, 5, 0，则输出答案为35，即从A2～A6。

HINT: 分治法，最大子序列和只存在三种情况
    1、在左边子序列
    2、跨越mid，在左边和右边
    3、在右边子序列
复杂度：n*log(n)

更优秀的算法收录在Dynamic programming中，其复杂度为n
 */

public class maxSumSubArray {
    public int maxSum(int[] nums){
        return divide(nums, 0, nums.length-1);
    }

    private int divide(int[] nums, int lo, int hi){
        if (lo == hi) return nums[lo];
        int mid = (lo+hi)/2;
        int leftMax = divide(nums, lo, mid);
        int rightMax = divide(nums, mid+1, hi);
        int lefCrossMax = Integer.MIN_VALUE, rightCrossMax = Integer.MIN_VALUE;
        int maxSoFar = 0;
        for (int i = mid; i >= lo; i--){
            maxSoFar += nums[i];
            lefCrossMax = Math.max(lefCrossMax, maxSoFar);
        }
        maxSoFar = 0;
        for (int i = mid+1; i <= hi; i++){
            maxSoFar += nums[i];
            rightCrossMax = Math.max(rightCrossMax, maxSoFar);
        }
        int crossMax = lefCrossMax + rightCrossMax;
        return Math.max(Math.max(leftMax, crossMax), rightMax);
    }

    public static void main(String[] args){
        System.out.println(new maxSumSubArray().maxSum(new int[]{-2, 11, 8, -4, -1, 16, 5, 0}));
    }
}
