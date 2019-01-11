package Exercises.BinarySearch;

/*

LeetCode No. 209 (CN)

给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。

示例:

输入: s = 7, nums = [2,3,1,2,4,3]
输出: 2
解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
进阶:

如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。

HINT：因为是正整数数组，因此只要增大范围，子数组和必然增大。一旦发现一个大于要求的子数组和，我们就可以尝试缩短这个范围。
 */


public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int i = 0, j = 0;
        int minLen = Integer.MAX_VALUE;
        int sum = 0;

        while (j < nums.length) {
            sum += nums[j];
            j++;
            while (sum >= s) {
                minLen = Math.min(minLen, j - i);
                sum -= nums[i];
                i++;
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public int solveInBinarySearch(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            sum[i] = sum[i-1] + nums[i - 1];
        }
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int j = binarySearch(i + 1, n + 1, sum, sum[i] + s);     // 对每个元素向后二分查找满足条件的最小元素
            if (j >= n + 1) break;
            minLen = Math.min(minLen, j - i);
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    private int binarySearch(int lo, int hi, int[] nums, int target) {
        while (lo < hi) {
            int mid = (hi - lo) / 2 + lo;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumSizeSubarraySum().solveInBinarySearch(7, new int[]{2, 3, 1, 2, 4, 3}));
    }
}
