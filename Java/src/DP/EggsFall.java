package DP;

public class EggsFall {
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        return helper2(K, N, dp);
    }

    // DP1, O(k*N^2)
    private int helper(int K, int N, int[][] dp){
        if (N <= 1) return N;
        if (K == 1) return N;
        if (dp[K][N] > 0) return dp[K][N];

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int left = helper(K-1, i-1, dp);
            int right = helper(K, N-i, dp);
            min = Math.min(min, Math.max(left, right) + 1);
        }

        dp[K][N] = min;
        return min;
    }

    // DP2, O(k*logN)    因为left和right是单调函数，所以可以使用二分法，找到两个单调函数相交的一点。详见印象笔记
    private int helper2(int K, int N, int[][] dp){
        if (N <= 1) return N;
        if (K == 1) return N;
        if (dp[K][N] > 0) return dp[K][N];

        int min = Integer.MAX_VALUE;
        int lo = 1, hi = N;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            int left = helper2(K-1, mid-1, dp);     // left 递增函数
            int right = helper2(K, N - mid, dp);        // right 递减函数
            min = Math.min(min, Math.max(left, right) + 1);
            if (left < right) {
                lo = mid + 1;
            } else if (left > right) {
                hi = mid - 1;
            } else {
                break;
            }
        }
        dp[K][N] = min;
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new EggsFall().superEggDrop(2, 9));
    }
}
