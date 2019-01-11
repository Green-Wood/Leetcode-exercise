package Exercises.BinarySearch;
/*
LeetCode No. 34 (CN)

给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

你的算法时间复杂度必须是 O(log n) 级别。

如果数组中不存在目标值，返回 [-1, -1]。

示例 1:

输入: nums = [5,7,7,8,8,10], target = 8
输出: [3,4]
示例 2:

输入: nums = [5,7,7,8,8,10], target = 6
输出: [-1,-1]

 */
public class FirstAndLastPositionInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] ret = {-1, -1};
        int position;
        position = binarySearch(nums, target);
        if (position >= nums.length || nums[position] != target) return ret;     // 如果不存在
        else ret[0] = position;
        position = binarySearch(nums, target + 1);              // 搜索target+1的位置
        ret[1] = position - 1;                    // target+1的最前位置的前面就是target最后一个元素的位置
        return ret;
    }

    private int binarySearch(int[] nums, int target) {
        int lo = 0, hi = nums.length;
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
}
