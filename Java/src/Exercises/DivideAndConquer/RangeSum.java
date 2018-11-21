package Exercises.DivideAndConquer;

/*
给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。

说明:
最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。n*log(n)

示例:

输入: nums = [-2,5,-1], lower = -2, upper = 2,
输出: 3
解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。

HINT: Merge Sort
 */
public class RangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; ++i)
            sums[i + 1] = sums[i] + nums[i];
        return countWhileMergeSort(sums, 0, n + 1, lower, upper);
    }

    private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
        if (end - start <= 1) return 0;
        int mid = (start + end) / 2;
        int count = countWhileMergeSort(sums, start, mid, lower, upper)
                + countWhileMergeSort(sums, mid, end, lower, upper);
        int j = mid, k = mid;
        for (int i = start; i < mid; ++i) {                         // 求出该区间中有多少符合条件的区间和
            while (k < end && sums[k] - sums[i] < lower) k++;
            while (j < end && sums[j] - sums[i] <= upper) j++;    // 可能出现多个与upper相同的区间和
            count += j - k;
        }

        long[] cache = new long[end - start];                   // 使用额外空间的 MergeSort
        int i = start, t = 0;
        j = mid;
        while (i < mid && j < end){
            if (sums[i] < sums[j]) cache[t++] = sums[i++];
            else cache[t++] = sums[j++];
        }
        while (i < mid) cache[t++] = sums[i++];
        while (j < end) cache[t++] = sums[j++];

        System.arraycopy(cache, 0, sums, start, cache.length);
        return count;
    }

    public static void main(String[] args){
        System.out.println(new RangeSum().countRangeSum(new int[]{-2147483647,0,-2147483647,2147483647}, -564, 3864));
    }
}