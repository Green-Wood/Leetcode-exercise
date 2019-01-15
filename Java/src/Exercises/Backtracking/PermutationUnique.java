package Exercises.Backtracking;

import java.util.*;
/*
给定一个可包含重复数字的序列，返回所有不重复的全排列。

示例:

输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
 */
public class PermutationUnique {
    public List<List<Integer>> permuteUnique(int[] nums) {
        return permuteMap(nums);
    }

    // 使用Set来避免重复，但这样会检查每一种可能性，时间较长
    private List<List<Integer>> permuteSet(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();
        helper(0, nums, set);
        return new ArrayList<>(set);
    }

    private void helper(int i, int[] nums, Set<List<Integer>> set){
        if (i == nums.length-1) {
            List<Integer> ans = new ArrayList<>();
            for (int num: nums) {
                ans.add(num);
            }
            set.add(ans);
        } else {
            for (int j = i; j < nums.length; j++) {
                swap(i, j, nums);
                helper(i+1, nums, set);
                swap(i, j, nums);
            }
        }
    }

    private void swap(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 使用HashMap来记录可用元素的个数，避免重复
    private List<List<Integer>> permuteMap(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int n: nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        helper2Map(map, ans, list, nums.length);
        return ans;
    }

    private void helper2Map(Map<Integer, Integer> map, List<List<Integer>> ans, ArrayList<Integer> list, int length) {
        if (list.size() == length) {
            ans.add((List<Integer>) list.clone());
        } else {
            for (int key: map.keySet()) {
                if (map.get(key) > 0) {
                    map.put(key, map.get(key) - 1);
                    list.add(key);
                    helper2Map(map, ans, list, length);
                    list.remove(list.size()-1);
                    map.put(key, map.get(key) + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> ans = new PermutationUnique().permuteUnique(new int[]{1, 1, 2});
        for (List<Integer> l: ans) {
            for (int i: l) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
