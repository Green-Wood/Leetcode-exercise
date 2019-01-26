package Array;
/*
如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。

例如，以下数列为等差数列:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
以下数列不是等差数列。

1, 1, 2, 5, 7


数组 A 包含 N 个数，且索引从0开始。数组 A 的一个子数组划分为数组 (P, Q)，P 与 Q 是整数且满足 0<=P<Q<N 。

如果满足以下条件，则称子数组(P, Q)为等差数组：

元素 A[P], A[p + 1], ..., A[Q - 1], A[Q] 是等差的。并且 P + 1 < Q 。

函数要返回数组 A 中所有为等差数组的子数组个数。



示例:

A = [1, 2, 3, 4]

返回: 3, A 中有三个子等差数组: [1, 2, 3], [2, 3, 4] 以及自身 [1, 2, 3, 4]。

HINT: 若在A中[i, j]为等差数列，若A[j + 1]与A[j]的差和之前等差数列的差相同，那么A[i, j + 1]也是等差数列，否则A[i, j + 1]不是等差数列
 */
public class ArithmeticSlices {
    public int brustForce(int[] A) {
        int count = 0;
        for (int i = 0; i < A.length - 2; i++) {
            int delta = A[i + 1] - A[i];
            for (int j = i + 1; j < A.length - 1; j++) {
                if (A[j + 1] - A[j] == delta) {
                    count++;
                } else {
                    break;
                }
            }
        }
        return count;
    }

    public int naiveDP(int[] A) {
        int[] dp = new int[A.length];     // dp[i] is the number of arithmetic slices which are end with A[i]
        int sum = 0;
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = dp[i - 1] + 1;
                sum += dp[i];
            }
        }
        return sum;
    }

    public int constanSpaceDP(int[] A) {
        int curr = 0;                    // curr is the number of arithmetic slices which are end with A[i]
        int sum = 0;
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                curr++;
                sum += curr;
            } else {
                curr = 0;
            }
        }
        return sum;
    }
}
