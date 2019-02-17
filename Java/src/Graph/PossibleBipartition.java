package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
给定一组 N 人（编号为 1, 2, ..., N）， 我们想把每个人分进任意大小的两组。

每个人都可能不喜欢其他人，那么他们不应该属于同一组。

形式上，如果 dislikes[i] = [a, b]，表示不允许将编号为 a 和 b 的人归入同一组。

当可以用这种方法将每个人分进两组时，返回 true；否则返回 false。



示例 1：

输入：N = 4, dislikes = [[1,2],[1,3],[2,4]]
输出：true
解释：group1 [1,4], group2 [2,3]
示例 2：

输入：N = 3, dislikes = [[1,2],[1,3],[2,3]]
输出：false
示例 3：

输入：N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
输出：false


提示：

1 <= N <= 2000
0 <= dislikes.length <= 10000
1 <= dislikes[i][j] <= N
dislikes[i][0] < dislikes[i][1]
对于 dislikes[i] == dislikes[j] 不存在 i != j


HINT: 判断一个图是否为二分图的标准做法
 */

public class PossibleBipartition {

    // My version
    public boolean possibleBipartition(int N, int[][] dislikes) {
        int[] colors = new int[N + 1];
        int[][] matrix = new int[N + 1][N + 1];
        for (int[] pair: dislikes) {
            matrix[pair[0]][pair[1]] = 1;
            matrix[pair[1]][pair[0]] = 1;
        }
        for (int i = 1; i <= N; i++) {
            if (colors[i] == 0 && !dfs(matrix, colors, i, 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int[][] dislikes, int[] colors, int i, int c) {
        if (colors[i] != 0) {
            return colors[i] == c;
        }
        colors[i] = c;
        int next = c == 1 ? 2 : 1;
        for (int j = 1; j < colors.length; j++) {
            if (dislikes[i][j] == 1) {
                if (!dfs(dislikes, colors, j, next)) return false;
            }
        }
        return true;
    }

    // Template version
    public boolean Bipartition(int N, int[][] dislikes) {
        List<Integer>[] adj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList();
        }
        for (int[] pairs: dislikes) {
            adj[pairs[0]].add(pairs[1]);
            adj[pairs[1]].add(pairs[0]);
        }
        Map<Integer, Integer> colors = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            if (!colors.containsKey(i) && !DFS(adj, colors, i, 0))
                return false;
        }
        return true;
    }

    private boolean DFS(List<Integer>[] adj, Map<Integer, Integer> colors, int node, int currColor) {
        if (colors.containsKey(node)) {
            return colors.get(node) == currColor;
        }
        colors.put(node, currColor);
        for (int i: adj[node]) {
            if (!DFS(adj, colors, i, currColor ^ 1))        //XOR
                return false;
        }
        return true;
    }


    public static void main(String[] args) {
        int[][] dislikes = new int[][]{
                {1, 2},
                {1, 3},
                {2, 3}
        };
        System.out.println(new PossibleBipartition().Bipartition(4, dislikes));
    }
}
