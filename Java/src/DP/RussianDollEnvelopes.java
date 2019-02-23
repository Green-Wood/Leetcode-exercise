package DP;

import java.util.Arrays;
/*
给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。

请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。

说明:
不允许旋转信封。

示例:

输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
输出: 3
解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 */









public class RussianDollEnvelopes {
    // my version O(n^2)
    // can be optimize in step 2 : find LIS to O(NlogN)
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) return 0;
        Arrays.sort(envelopes, ((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]));   // sort array
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < dp.length; i++) {               // find LIS
            int currMax = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1])
                    currMax = Math.max(currMax, dp[j]);
            }
            dp[i] += currMax;
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // best practice  O(NlogN)
    public int bestPractice(int[][] envelopes) {
        Arrays.sort(envelopes, ((o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]));
        int[] dp = new int[envelopes.length];
        int result = 0;
        for (int[] e: envelopes) {
            int i = Arrays.binarySearch(dp, 0, result, e[1]);
            if (i < 0) i = -(i + 1);
            dp[i] = e[1];
            if (i == result) result++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] envelopes = new int[][]{
                {1, 1}, {1, 1}, {1, 1}, {1, 1}
        };
        System.out.println(new RussianDollEnvelopes().maxEnvelopes(envelopes));
    }
}
