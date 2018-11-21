package Exercises.KthSmallest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
Leetcode No. 378(CN)


给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
请注意，它是排序后的第k小元素，而不是第k个元素。

示例:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

返回 13。
说明:
你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n2 。
 */

public class KthSmallestInMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        return binarySearchValueRange(matrix, k);
    }

    private int sortingBased(int[][] matrix, int k) {
        int n = matrix.length;
        int[] nums = new int[n * n];
        int t = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nums[t++] = matrix[i][j];
            }
        }

        Arrays.sort(nums);
        return nums[k - 1];
    }

    private int binarySearchValueRange(int[][] matrix, int k) {       // 在值的范围进行二分查找
        int n = matrix.length;
        int lo = matrix[0][0], hi = matrix[n-1][n-1];
        for (int cnt = 0; lo < hi; cnt = 0) {
            int mid = lo + (hi - lo) / 2;
            for (int i = 0, j = n - 1; i < n; i++) {
                while (j >= 0 && matrix[i][j] > mid) j--;
                cnt += j + 1;
            }
            if (cnt < k) lo = mid + 1;
            else hi = mid;
        }

        return lo;
    }

    private int PriorityWithArray(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> matrix[o[0]][o[1]]));
        for (int i = 0; i < matrix.length; i++) {
            pq.add(new int[]{i, 0});
        }
        for (int i = 0; i < k -1 ; i++) {
            int[] curr = pq.poll();
            if (curr[1] + 1< matrix[0].length){
                curr[1]++;
                pq.add(curr);
            }
        }
        int[] ans = pq.poll();
        return matrix[ans[0]][ans[1]];
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15},
        };
        System.out.println(new KthSmallestInMatrix().kthSmallest(matrix, 8));
    }
}
