package DP;
/*
在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。

示例:

输入:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

输出: 4
 */
public class MaximalSquare {
    // dp[i][j] 表示右下角在i，j处的正方形的最大边长
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int n = matrix.length, m = matrix[0].length;
        int[][] dp = new int[n+1][m+1];
        int maxSideLength = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (matrix[i-1][j-1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]) + 1;
                    maxSideLength = Math.max(maxSideLength, dp[i][j]);
                }
            }
        }
        return maxSideLength * maxSideLength;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
        };
        System.out.println(new MaximalSquare().maximalSquare(matrix));
    }
}
