package Exercises.UnionFind;
/*

LeetCode No. 200 (CN)

给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。

示例 1:

输入:
11110
11010
11000
00000

输出: 1
示例 2:

输入:
11000
11000
00100
00011

输出: 3
 */
public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        return numIslandsDFS(grid);
    }

    // Union-find template
    private class UnionFind {
        private int count;
        private int[] parent;
        private int[] rank;        // 以此为根结点的子树的高度

        UnionFind(int n) {
            count = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int i) {
            while (i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return i;
        }

        void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            if (rank[rootP] > rank[rootQ]) {
                parent[rootQ] = rootP;
            } else {
                parent[rootP] = rootQ;
                if (rank[rootP] == rank[rootQ]) {
                    rank[rootQ]++;
                }
            }
            count--;
        }

        int getCount() {
            return count;
        }
    }

    // Union-find version
    private int numIslandsUnionFind(char[][] grid) {
        if (grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int count0 = 0;
        UnionFind uf = new UnionFind(m * n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    if (j - 1 >= 0 && grid[i][j-1] == '1') uf.union(n * i + j, n * i + j - 1);
                    if (j + 1 < n && grid[i][j+1] == '1') uf.union(n * i + j, n * i + j + 1);
                    if (i - 1 >= 0 && grid[i-1][j] == '1') uf.union(n * i + j, n * (i - 1) + j);
                    if (i + 1 < m && grid[i+1][j] == '1') uf.union(n * i + j, n * (i + 1) + j);
                } else {
                    count0++;
                }
            }
        }
        return uf.getCount() - count0;
    }

    // DFS version
    private int numIslandsDFS(char[][] grid) {
        if (grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1') return;
        grid[i][j] = '0';
        dfs(grid, i, j-1);
        dfs(grid, i-1, j);
        dfs(grid, i, j+1);
        dfs(grid, i+1, j);
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '0', '1', '0'},
                {'0', '1', '0', '1', '0'},
                {'1', '0', '0', '0', '0'},
                {'0', '0', '0', '1', '1'},
        };
        System.out.println(new NumberOfIslands().numIslands(grid));
    }
}
