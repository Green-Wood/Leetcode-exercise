package Array;

/*
给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。

请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。

你可以假设 nums1 和 nums2 不同时为空。

示例 1:

nums1 = [1, 3]
nums2 = [2]

中位数是 2.0
示例 2:

nums1 = [1, 2]
nums2 = [3, 4]

中位数是 (2 + 3)/2 = 2.5

HINT: merge sort
 */

public class findMedianInTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int[] nums = new int[n1 + n2];
        int i = 0, j = 0;
        int k = 0;
        while (i < n1 && j < n2){
            if (nums1[i] < nums2[j]) nums[k++] = nums1[i++];
            else nums[k++] = nums2[j++];
        }
        while (i < n1) nums[k++] = nums1[i++];
        while (j < n2) nums[k++] = nums2[j++];
        double res;
        int mid = (n1 + n2) / 2;
        if ((n1 + n2) % 2 == 0){
            res = (nums[mid] + nums[mid-1]) / 2.0;
        } else {
            res = nums[mid];
        }
        return res;
    }

    public static void main(String[] args){
        System.out.println(new findMedianInTwoSortedArrays().findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }
}
