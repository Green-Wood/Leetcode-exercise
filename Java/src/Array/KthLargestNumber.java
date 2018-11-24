package Array;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
/*

215.

在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:

输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
示例 2:

输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
说明:

你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。


HINT：三种方法
 */
public class KthLargestNumber {
    public int findKthLargest(int[] nums, int k) {
        return sort(nums, k);
    }

    // 排序，O(NlogN)
    private int sort(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        return nums[n - k];
    }

    // 建立最小堆，O(Nlogk)
    private int heap(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n: nums) {
            pq.add(n);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    // 使用了快排的选择，O(N)，在对数组随机打乱之后能够提高效率
    private int QuickSelect(int[] nums, int k) {
        k = nums.length - k;
        int lo = 0, hi = nums.length-1;
        while (lo < hi) {
            int p = partition(nums, lo, hi);
            if (p > k) {
                hi = p - 1;
            } else if (p < k) {
                lo = p + 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    private int partition(int[] nums, int lo, int hi) {
        int pivot = nums[lo];
        int i = lo, j = hi;
        while (i < j) {
            while (i < j && nums[j] >= pivot) j--;
            nums[i] = nums[j];
            while (i < j && nums[i] <= pivot) i++;
            nums[j] = nums[i];
        }
        nums[i] = pivot;
        return i;
    }
}
