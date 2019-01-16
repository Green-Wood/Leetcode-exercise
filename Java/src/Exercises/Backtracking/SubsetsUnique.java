package Exercises.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。

示例:

输入: [1,2,2]
输出:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 */
public class SubsetsUnique {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        backtracking(lists, new ArrayList<>(), nums, 0);
        return lists;
    }

    private void backtracking(List<List<Integer>> lists, List<Integer> currList, int[] nums, int start) {
        lists.add(new ArrayList<>(currList));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            currList.add(nums[i]);
            backtracking(lists, currList, nums, i + 1);
            currList.remove(currList.size() - 1);
        }
    }
}
