package DP;
/*
给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

例如，给定三角形：

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。


HINT: 题目要求自顶向下，但其实可以自底向上来找，这样更加方便。
 */

import java.util.List;

public class TrianglePathSum {
    public int minimumTotal(List<List<Integer>> triangle) {
        int height = triangle.size();
        int[] dp = new int[height];
        for (int i = 0; i < height; i++) {
            dp[i] = triangle.get(height - 1).get(i);
        }
        for (int i = height - 2; i >= 0; i--) {
            List<Integer> l = triangle.get(i);
            for (int j = 0; j < l.size(); j++) {
                dp[j] = Math.min(dp[j], dp[j+1]) + l.get(j);
            }
        }
        return dp[0];
    }
}
