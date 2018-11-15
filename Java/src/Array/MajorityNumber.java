package Array;
/*
给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在众数。

示例 1:

输入: [3,2,3]
输出: 3
示例 2:

输入: [2,2,1,1,1,2,2]
输出: 2
 */
public class MajorityNumber {
    public int majorityElement(int[] nums) {      // 遇到相同的数count++，遇到不同的数count--，count为0时换个数开始计数
        int ans = 0;
        int count = 0;
        for (int n: nums) {
            if (count == 0) ans = n;
            if (ans == n) count++;
            else count--;
        }
        return ans;
    }
}
