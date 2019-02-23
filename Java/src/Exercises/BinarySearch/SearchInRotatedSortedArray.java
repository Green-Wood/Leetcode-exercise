package Exercises.BinarySearch;
/*
假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

你可以假设数组中不存在重复的元素。

你的算法时间复杂度必须是 O(log n) 级别。

示例 1:

输入: nums = [4,5,6,7,0,1,2], target = 0
输出: 4
示例 2:

输入: nums = [4,5,6,7,0,1,2], target = 3
输出: -1
 */
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int n = nums.length;
        int lo = 0, hi = n - 1;
        while (lo < hi) {                      // 使用二分搜索来寻找最小值的下标
            int mid = (hi - lo) / 2 + lo;
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        int sIndex = lo;
        lo = 0;
        hi = n;
        while (lo < hi) {
            int mid = (hi - lo) / 2 + lo;
            int realMid = (mid + sIndex) % n;              // 使用最小下标来对相对值进行转换
            if (nums[realMid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        int realLo = (lo + sIndex) % n;
        return nums[realLo] == target ? realLo : -1;
    }

    public static void main(String[] args) {
        System.out.print(new SearchInRotatedSortedArray().search(new int[]{5, 1, 3}, 1));
    }
}
