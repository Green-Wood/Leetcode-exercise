package Graph;

import java.util.LinkedList;
import java.util.Queue;


/*

No. 934 (CN)

在给定的二维二进制数组 A 中，存在两座岛。（岛是由四面相连的 1 形成的一个最大组。）

现在，我们可以将 0 变为 1，以使两座岛连接起来，变成一座岛。

返回必须翻转的 0 的最小数目。（可以保证答案至少是 1。）



示例 1：

输入：[[0,1],[1,0]]
输出：1
示例 2：

输入：[[0,1,0],[0,0,0],[0,0,1]]
输出：2
示例 3：

输入：[[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
输出：1


HINT:  DFS + BFS
 */



public class ShortestBridge {

    private boolean[][] visited;
    private int[][] A;
    private Queue<int[]> queue;
    private int[][] dirs;

    public int shortestBridge(int[][] A) {
        int m = A.length, n = A[0].length;
        this.A = A;
        visited = new boolean[m][n];
        queue = new LinkedList<>();                         //  a queue to run BFS
        dirs = new int[][] {
                {0, -1}, {-1, 0}, {0, 1}, {1, 0},           // a two-dimensional array to store directions to go
        };

        // Using DFS to find a island, find only one is enough
        boolean findOne = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n && !findOne; j++) {
                if (A[i][j] == 1) {
                    dfs(i, j);
                    findOne = true;
                }
            }
        }

        // Using BFS to expand this island
        int step = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                for (int[] dir: dirs) {
                    int i = curr[0] + dir[0];
                    int j = curr[1] + dir[1];
                    if (i >= 0 && i < m && j >= 0 && j < n && !visited[i][j]) {
                        if (A[i][j] == 1) {
                            return step;
                        } else {
                            queue.add(new int[]{i, j});
                            visited[i][j] = true;
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private void dfs(int i, int j) {
        if (i < 0 || i >= visited.length || j < 0 || j >= visited[0].length || visited[i][j] || A[i][j] == 0)
            return;
        visited[i][j] = true;
        queue.add(new int[]{i, j});
        for (int[] dir: dirs) {
            dfs(i + dir[0], j + dir[1]);
        }
    }

    public static void main(String[] args) {
        System.out.println(new ShortestBridge().shortestBridge(new int[][] {
                {0, 1, 0},
                {0, 0, 0},
                {0, 0, 1},
        }));
    }
}
