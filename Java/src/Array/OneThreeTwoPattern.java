package Array;

import java.util.ArrayDeque;
import java.util.Deque;
/*
给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。

注意：n 的值小于15000。

示例1:

输入: [1, 2, 3, 4]

输出: False

解释: 序列中不存在132模式的子序列。
示例 2:

输入: [3, 1, 4, 2]

输出: True

解释: 序列中有 1 个132模式的子序列： [1, 4, 2].
示例 3:

输入: [-1, 3, 2, 0]

输出: True

解释: 序列中有 3 个132模式的的子序列: [-1, 3, 2], [-1, 3, 0] 和 [-1, 2, 0].
 */
public class OneThreeTwoPattern {
    // TLE  O(n^3)
    public boolean brustForce(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] < nums[k] && nums[k] < nums[j])
                        return true;
                }
            }
        }
        return false;
    }

    // search nums[k] in the value range (min, nums[j])    665ms   O(n^2)
    public boolean optimizedForce(int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            min = Math.min(min, nums[j]);
            for (int k = j + 1; k < n; k++) {
                if (nums[k] > min && nums[k] < nums[j])
                    return true;
            }
        }
        return false;
    }

    // two-pass using stack 23ms
    // in the first loop, we find the best nums[i] for every nums[j], and store it in min[j]
    // in the second loop, we using a stack to find a nums[k] that fit in the value range (min[i], nums[i])
    public boolean find132pattern(int[] nums) {
        int[] min = new int[nums.length];
        int currMin = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            currMin = Math.min(currMin, nums[i]);
            min[i] = currMin;
        }
        Deque<Integer> stack = new ArrayDeque<>();             // stack中的数字总是单调递增的，因为每遇到nums[i]就从栈中弹出比
        for (int i = nums.length - 1; i > 0; i--) {            // nums[i] 小的数字。这么做的依据是：min数组从后到前是递增的
            if (stack.isEmpty()) {
                stack.push(nums[i]);
            } else {
                while (!stack.isEmpty() && stack.peek() < nums[i]) {
                    if (stack.pop() > min[i]) return true;
                }
                stack.push(nums[i]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new OneThreeTwoPattern().find132pattern(new int[]{1, 0, 1, -4, -3}));
    }
}
