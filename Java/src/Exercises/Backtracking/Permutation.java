package Exercises.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
给定一个没有重复数字的序列，返回其所有可能的全排列。

示例:

输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */
public class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        permuteWithUniqueElement(lists, new ArrayList<>(), nums);
        return lists;
    }

    private void permuteWithUniqueElement(List<List<Integer>> lists, List<Integer> currList, int[] nums) {
        if (currList.size() == nums.length) {
            lists.add(new ArrayList<>(currList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (currList.contains(nums[i])) continue;               // if list already contains this unique element
                currList.add(nums[i]);
                permuteWithUniqueElement(lists, currList, nums);
                currList.remove(currList.size() - 1);
            }
        }
    }
}
