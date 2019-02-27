package Exercises.MonotoneStack;

import java.util.ArrayDeque;
import java.util.Deque;

/*
返回 A 的最短的非空连续子数组的长度，该子数组的和至少为 K 。

如果没有和至少为 K 的非空子数组，返回 -1 。



示例 1：

输入：A = [1], K = 1
输出：1
示例 2：

输入：A = [1,2], K = 4
输出：-1
示例 3：

输入：A = [2,-1,2], K = 3
输出：3


提示：

1 <= A.length <= 50000
-10 ^ 5 <= A[i] <= 10 ^ 5
1 <= K <= 10 ^ 9

HINT: sum数组为A数组的前缀和，sum[j] - sum[i] 为 数组A[i: j]之和
因此，我们的目的是对于任意一个sum[j]找到一个最近的i，使得sum[j] - sum[i] >= K

We maintain a increasing queue here because,
given a new B[i], the larger element on the left are inferior than B[i] as a candidate
to make some future element B[j] >= B[i] + K (j > i).
 */
public class ShortestSubarraySumAtLeastK {
    public int shortestSubarray(int[] A, int K) {
        int n = A.length;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i+1] = sum[i] + A[i];
        }
        int minLen = Integer.MAX_VALUE;
        Deque<Integer> PLE = new ArrayDeque<>();
        for (int i = 0; i < n + 1; i++) {
            while (!PLE.isEmpty() && sum[i] - sum[PLE.getFirst()] >= K) {
                minLen = Math.min(minLen, i - PLE.pollFirst());
            }
            while (!PLE.isEmpty() && sum[i] <= sum[PLE.getLast()]) {
                PLE.pollLast();
            }
            PLE.addLast(i);
        }
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }

    public static void main(String[] args) {
        System.out.println(new ShortestSubarraySumAtLeastK().shortestSubarray(new int[]{77,19,35,10,-14}, 19));
    }
}
