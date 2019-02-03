package Exercises;
/*
一个N x N的网格(grid) 代表了一块樱桃地，每个格子由以下三种数字的一种来表示：

0 表示这个格子是空的，所以你可以穿过它。
1 表示这个格子里装着一个樱桃，你可以摘到樱桃然后穿过它。
-1 表示这个格子里有荆棘，挡着你的路。
你的任务是在遵守下列规则的情况下，尽可能的摘到最多樱桃：

从位置 (0, 0) 出发，最后到达 (N-1, N-1) ，只能向下或向右走，并且只能穿越有效的格子（即只可以穿过值为0或者1的格子）；
当到达 (N-1, N-1) 后，你要继续走，直到返回到 (0, 0) ，只能向上或向左走，并且只能穿越有效的格子；
当你经过一个格子且这个格子包含一个樱桃时，你将摘到樱桃并且这个格子会变成空的（值变为0）；
如果在 (0, 0) 和 (N-1, N-1) 之间不存在一条可经过的路径，则没有任何一个樱桃能被摘到。
示例 1:

输入: grid =
[[0, 1, -1],
 [1, 0, -1],
 [1, 1,  1]]
输出: 5
解释：
玩家从（0,0）点出发，经过了向下走，向下走，向右走，向右走，到达了点(2, 2)。
在这趟单程中，总共摘到了4颗樱桃，矩阵变成了[[0,1,-1],[0,0,-1],[0,0,0]]。
接着，这名玩家向左走，向上走，向上走，向左走，返回了起始点，又摘到了1颗樱桃。
在旅程中，总共摘到了5颗樱桃，这是可以摘到的最大值了。


不要考虑成来回两趟，而应该想象为有两个人从 (0, 0) 同时出发走向 (N-1, N-1) ，并在走到相同格子时只计算一个grid。

T(i, j, p, q) = grid[i][j] + grid[p][q] +
Max(T(i, j-1, p-1, q) + T(i, j-1, p, q-1) + T(i-1, j, p-1, q) + T(i-1, j, p, q-1))    (当i != p 时，否则只算一个grid[i][j])
因为有 i + j = p + q = n
T(i, j, p, q) = T(n, i, j)
T(i, j-1, p-1, q) = T(i, n-i-1, p-1, n-p) = T(n-1, i, p-1)
T(i, j-1. p, q-1) = T(i, n-i-1, p, n-p-1) = T(n-1, i, p)
T(i-1, j, p, q-1) = T(i-1, n-i, p, n-p-1) = T(n-1, i-1, p)
T(i-1, j, p-1, q) = T(i-1, n-i, p, n-p) = T(n-1, i-1, p-1)

所以有： T(n, i, p) = grid[i][n-i] + grid[p][n-p] + Max(T(n-1, i, p) + T(n-1, i-1, p) + T(n-1, i, p-1) + T(n-1, i-1, p-1))
其中 n属于[1, 2N-2], i, p都属于[0, N-1]
 */
public class CherryPickUp {
    public int cherryPickup(int[][] grid) {
        int N = grid.length;
        int M = 2 * N - 2;
        int[][] dp = new int[N][N];
        dp[0][0] = grid[0][0];                              // 自右下向左上迭代，使得能够在原dp[][]矩阵上进行迭代
        for (int n = 1; n <= M; n++) {
            for (int i = N - 1; i >= 0; i--) {
                for (int p = N - 1; p >= 0; p--) {
                    int j = n - i, q = n - p;
                    if (j < 0 || j >= N || q < 0 || q >= N || grid[i][j] < 0 || grid[p][q] < 0) {
                        dp[i][p] = -1;                     // 设为当前不可到达
                        continue;
                    }
                    if (i >= 1) dp[i][p] = Math.max(dp[i][p], dp[i-1][p]);
                    if (p >= 1) dp[i][p] = Math.max(dp[i][p], dp[i][p-1]);
                    if (i >= 1 && p >= 1) dp[i][p] = Math.max(dp[i][p], dp[i-1][p-1]);
                    if (dp[i][p] >= 0) dp[i][p] += grid[i][j] + (i == p ? 0 : grid[p][q]);// 对于不可到达的地方，不增加grid
                }
            }
        }
        return Math.max(dp[N-1][N-1], 0);
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 1, -1},
                {1, -1, 1},
                {-1, 1, 1},
        };
        System.out.println(new CherryPickUp().cherryPickup(grid));
    }
}
