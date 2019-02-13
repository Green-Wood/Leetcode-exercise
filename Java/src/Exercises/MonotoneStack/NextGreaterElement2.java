package Exercises.MonotoneStack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
/*
给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，
这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。

示例 1:

输入: [1,2,1]
输出: [2,-1,2]
解释: 第一个 1 的下一个更大的数是 2；
数字 2 找不到下一个更大的数；
第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 */
public class NextGreaterElement2 {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] next = new int[nums.length];
        Arrays.fill(next, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < 2 * n; i++) {
            int curr = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peek()] < curr) {
                next[stack.pop()] = curr;
            }
            if (i < n) stack.push(i);
        }
        return next;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new NextGreaterElement2().nextGreaterElements(new int[]{1, 2, 3, 4, 5})));
    }
}
