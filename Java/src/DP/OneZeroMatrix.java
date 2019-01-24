package DP;

import java.util.ArrayDeque;
import java.util.Deque;

public class OneZeroMatrix {
    // using two sweep dp
    /*
The distance of a cell from 0 can be calculated if we know the nearest distance for all the neighbours,
in which case the distance is minimum distance of any neightbour + 1. And, instantly, the word come to mind DP!!
For each 1, the minimum path to 0 can be in any direction. So, we need to check all the 4 direction.
In one iteration from top to bottom, we can check left and top directions, and we need another iteration
from bottom to top to check for right and bottom direction.

Algorithm

1. Iterate the matrix from top to bottom-left to right:
i.e., minimum of the current dist and distance from top or left neighbour +1, that would have been
already calculated previously in the current iteration.
2. Now, we need to do the back iteration in the similar manner: from bottom to top-right to left:
i.e. minimum of current dist and distances calculated from bottom and right neighbours,
that would be already available in current iteration.
     */
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dist = new int[m][n];
        int maxDist = m * n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) dist[i][j] = maxDist;
                if (i > 0)
                    dist[i][j] = Math.min(dist[i][j], dist[i-1][j] + 1);
                if (j > 0)
                    dist[i][j] = Math.min(dist[i][j], dist[i][j-1] + 1);
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i < m - 1)
                    dist[i][j] = Math.min(dist[i][j], dist[i+1][j] + 1);
                if (j < n - 1)
                    dist[i][j] = Math.min(dist[i][j], dist[i][j+1] + 1);
            }
        }
        return dist;
    }

    public int[][] bfsVersion(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Deque<int[]> queue = new ArrayDeque<>();
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) queue.add(new int[]{i, j});
                else dist[i][j] = Integer.MAX_VALUE;
            }
        }
        int[][] dirs = new int[][]{
                {-1, 0}, {0, -1}, {1, 0}, {0, 1}
        };

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] dir: dirs) {
                int i = dir[0] + cell[0];
                int j = dir[1] + cell[1];
                if (i < 0 || j < 0 || i >= m || j >= n || dist[i][j] <= dist[cell[0]][cell[1]] + 1) continue;
                queue.add(new int[]{i, j});
                dist[i][j] = dist[cell[0]][cell[1]] + 1;
            }
        }

        return dist;
    }
}
