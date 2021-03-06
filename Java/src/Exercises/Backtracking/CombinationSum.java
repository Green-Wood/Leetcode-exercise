package Exercises.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：

所有数字（包括 target）都是正整数。
解集不能包含重复的组合。
示例 1:

输入: candidates = [2,3,6,7], target = 7,
所求解集为:
[
  [7],
  [2,2,3]
]
示例 2:

输入: candidates = [2,3,5], target = 8,
所求解集为:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(candidates);
        backtracking(lists, new ArrayList<>(), candidates, target, 0);
        return lists;
    }

    public void backtracking(List<List<Integer>> lists, List<Integer> curr, int[] nums, int target, int start) {
        if (target < 0) return;
        else if (target == 0) lists.add(new ArrayList<>(curr));
        else {
            for (int i = start; i < nums.length; i++) {
                curr.add(nums[i]);
                backtracking(lists, curr, nums, target - nums[i], i);  // not i + 1 because we can reuse elements
                curr.remove(curr.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new CombinationSum().combinationSum(new int[]{2, 3, 6, 7}, 7);
        lists.forEach(System.out::println);
    }
}
