package Exercises.KthSmallest;
/*
Leetcode No. 668(CN)


几乎每一个人都用 乘法表。但是你能在乘法表中快速找到第k小的数字吗？

给定高度m 、宽度n 的一张 m * n的乘法表，以及正整数k，你需要返回表中第k 小的数字。

例 1：

输入: m = 3, n = 3, k = 5
输出: 3
解释:
乘法表:
1	2	3
2	4	6
3	6	9

第5小的数字是 3 (1, 2, 2, 3, 3).
例 2：

输入: m = 2, n = 3, k = 6
输出: 6
解释:
乘法表:
1	2	3
2	4	6

第6小的数字是 6 (1, 2, 2, 3, 4, 6).
 */
public class KthSmallestInMultiplyTable {
    public int findKthNumber(int m, int n, int k) {
        int lo = 1;
        int hi = m * n;
        for (int cnt = 0; lo < hi; cnt = 0) {
            int mid = lo + (hi - lo) / 2;
            for (int i = 1, j = n; i <= m; i++) {
                while (j >= 1 && i * j > mid) j--;
                cnt += j;
            }
            if (cnt < k) lo = mid + 1;
            else hi = mid;
        }

        return lo;
    }

    public static void main(String[] args) {
        System.out.println(new KthSmallestInMultiplyTable().findKthNumber(3, 3, 5));
    }
}
