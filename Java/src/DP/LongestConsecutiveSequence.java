package DP;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
给定一个未排序的整数数组，找出最长连续序列的长度。

要求算法的时间复杂度为 O(n)。

示例:

输入: [100, 4, 200, 1, 3, 2]
输出: 4
解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */
public class LongestConsecutiveSequence {
    // HashMap
    // 类似于DP的策略，currLen = leftLen + rightLen + 1
    public int longestConsecutive1(int[] nums) {
        int longest = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums){
            if (!map.containsKey(num)){
                int left = map.getOrDefault(num-1, 0);
                int right = map.getOrDefault(num+1, 0);
                int sum = left + right + 1;
                map.put(num, sum);
                map.put(num-left, sum);
                map.put(num+right, sum);
                longest = Math.max(longest, sum);
            }
        }
        return longest;
    }

    // HashSet
    // 看起来是n^2，但实际上是n，因为内层while循环最多循环n次
    public int longestConsecutive2(int[] nums){
        HashSet<Integer> set = new HashSet<>();
        int longest = 0;
        for (int num: nums){
            set.add(num);
        }
        for (int num: nums){
            if (!set.contains(num-1)){
                int currNum = num;
                int currLen = 1;
                while (set.contains(currNum+1)){
                    currLen++;
                    currNum++;
                }
                longest = Math.max(longest, currLen);
            }
        }
        return longest;
    }

    public static void main(String[] args){
        System.out.println(new LongestConsecutiveSequence().longestConsecutive2(new int[]{100, 4, 200, 1, 3, 2}));
    }
}
