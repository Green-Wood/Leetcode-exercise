package Exercises;

import java.util.Arrays;
import java.util.PriorityQueue;
/*
Problem No. 786  (CN)
一个已排序好的表 A，其包含 1 和其他一些素数.  当列表中的每一个 p<q 时，我们可以构造一个分数 p/q 。

那么第 k 个最小的分数是多少呢?  以整数数组的形式返回你的答案, 这里 answer[0] = p 且 answer[1] = q.

示例:
输入: A = [1, 2, 3, 5], K = 3
输出: [2, 5]
解释:
已构造好的分数,排序后如下所示:
1/5, 1/3, 2/5, 1/2, 3/5, 2/3.
很明显第三个最小的分数是 2/5.

输入: A = [1, 7], K = 1
输出: [1, 7]
注意:

A 的取值范围在 2 — 2000.
每个 A[i] 的值在 1 —30000.
K 取值范围为 1 —A.length * (A.length - 1) / 2






HINT:
This solution probably doesn't have the best runtime but it's really simple and easy to understand.

Says if the list is [1, 7, 23, 29, 47], we can easily have this table of relationships

1/47  < 1/29    < 1/23 < 1/7
7/47  < 7/29    < 7/23
23/47 < 23/29
29/47

So now the problem becomes "find the kth smallest element of (n-1) sorted list"
 */
public class KthSmallestPrimeFraction {
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        int n = A.length;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            int s1 = A[o1[0]] * A[o2[1]];          // 交叉互相乘了彼此的分母
            int s2 = A[o2[0]] * A[o1[1]];
            return s1 - s2;
        });

        for(int i = 0; i < n-1; i++) {
            pq.add(new int[]{ i, n-1 });         // 每次加进去一个数组，[0]为分子的index，[1]为分子的index
        }
        for(int i = 0; i < K-1; i++) {      // 当前的数组不满足要求时，向队列中添加一个分子相同，分母较小，即值较大一点的数
            int[] cur = pq.poll();
            if(cur[1] - 1 > cur[0]) {       // 保证每次迭代中时，数值最小的分数都在优先队列中
                cur[1]--;
                pq.add(cur);
            }
        }

        int[] ans = pq.poll();
        return new int[]{A[ans[0]], A[ans[1]]};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new KthSmallestPrimeFraction().
                kthSmallestPrimeFraction(new int[]{1, 2, 3, 5}, 3)));
    }
}
