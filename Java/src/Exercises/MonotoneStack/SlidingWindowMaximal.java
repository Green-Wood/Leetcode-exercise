package Exercises.MonotoneStack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
/*
给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位。

返回滑动窗口最大值。

示例:

输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7]
解释:

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
注意：

你可以假设 k 总是有效的，1 ≤ k ≤ 输入数组的大小，且输入数组不为空。
 */
public class SlidingWindowMaximal {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return new int[0];
        Deque<Integer> NPL = new ArrayDeque<>();     // nearest previous larger element
        int[] ans = new int[nums.length - k + 1];
        int r = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!NPL.isEmpty() && i - NPL.getFirst() >= k) {
                NPL.pollFirst();
            }
            while (!NPL.isEmpty() && nums[NPL.getLast()] < nums[i]) {          // decreasing deque
                NPL.pollLast();
            }
            NPL.addLast(i);
            if (i >= k - 1) {
                ans[r++] = nums[NPL.getFirst()];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SlidingWindowMaximal().
                maxSlidingWindow(new int[]{1, -1}, 1)));
    }
}
