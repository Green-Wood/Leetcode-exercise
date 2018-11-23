package Exercises.UnionFind;
/*

LeetCode No. (547)

班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。

给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。

示例 1:

输入:
[[1,1,0],
 [1,1,0],
 [0,0,1]]
输出: 2
说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
第2个学生自己在一个朋友圈。所以返回2。
示例 2:

输入:
[[1,1,0],
 [1,1,1],
 [0,1,1]]
输出: 1
说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1
 */
public class FriendCycle {

    public int findCircleNum(int[][] M) {
        return findCircleNumDFS(M);
    }

    // DFS version
    private int findCircleNumDFS(int[][] M) {
        boolean[] marked = new boolean[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (!marked[i]) {
                dfs(M, marked, i);
                count++;
            }
        }
        return count;
    }

    private void dfs(int[][] M, boolean[] marked, int v) {
        marked[v] = true;
        for (int i = 0; i < M.length; i++) {
            if (M[v][i] == 1 && !marked[i]) {
                dfs(M, marked, i);
            }
        }
    }

    // Union-find version
    private int findCycleNumUnionFind(int[][] M) {
        int n = M.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1) unionFind.union(i, j);
            }
        }
        return unionFind.getCount();
    }

    //  Union-find template
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

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1},
        };
        System.out.println(new FriendCycle().findCircleNum(matrix));
    }
}
