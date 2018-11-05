package DP;
/*
如果一个由 '0' 和 '1' 组成的字符串，是以一些 '0'（可能没有 '0'）后面跟着一些 '1'（也可能没有 '1'）的形式组成的，那么该字符串是单调递增的。

我们给出一个由字符 '0' 和 '1' 组成的字符串 S，我们可以将任何 '0' 翻转为 '1' 或者将 '1' 翻转为 '0'。

返回使 S 单调递增的最小翻转次数。



示例 1：

输入："00110"
输出：1
解释：我们翻转最后一位得到 00111.
示例 2：

输入："010110"
输出：2
解释：我们翻转得到 011111，或者是 000111。
示例 3：

输入："00011000"
输出：2
解释：我们翻转得到 00000000。
 */
public class FlipStringToMonotoneIncreasing {
    // 第一次扫描使用f0[i]数组来记录i的右边（包括i自己）存在的0的个数
    // 第二次扫描count表示i的左边（不包括i自己）存在的1的个数
    // 并同时假设i的左边都是0，i的右边都是1，算n次，取最小的那个
    public int minFlipsMonoIncr(String S) {
        int[] f0 = new int[S.length()+1];
        int count = 0;
        for (int i = S.length()-1; i >= 0; i--){
            if (S.charAt(i) == '0') count++;
            f0[i] = count;
        }
        count = 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < S.length() + 1; i++){
            int curr = count + f0[i];
            res = Math.min(res, curr);
            if (i == S.length()) break;
            if (S.charAt(i) == '1') count++;
        }
        return res;
    }
    /*
    if current digit is zero
	    flipToZero[i] = 0 + flipToZero[i-1]  //no need to flip the current digit and the previous digit must be zero
	    flipToOne[i] = 1 + min(flipToZero[i-1], flipToOne[i-1]) // need flip zero to one and the previous digit can be either zero or one
    if current digit is one
	    flipToZero[i] = 1 + flipToZero[i-1]  //need to flip one to zero and the previous digit must be zero
	    flipToOne[i] = 0 + min(flipToZero[i-1], flipToOne[i-1])  // no need to flip the current digit and the previous digit can be either zero or one

    https://leetcode.com/problems/flip-string-to-monotone-increasing/discuss/186797/Java-DP-solution
     */
    public int minFlipsMonoIncrDP(String S){
        int[][] dp = new int[S.length()+1][2];
        for (int i = 1; i <= S.length(); i++){
            if (S.charAt(i-1) == '0'){
                dp[i][0] = dp[i-1][0];
                dp[i][1] = 1 + Math.min(dp[i-1][0], dp[i-1][1]);
            } else {
                dp[i][0] = 1 + dp[i-1][0];
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][1]);
            }
        }
        return Math.min(dp[S.length()][0], dp[S.length()][1]);
    }

    public int minFlipMonoIncrDPSimple(String S){
        int f0 = 0, f1 = 0;
        for (int i = 0; i < S.length(); i++){
            f0 += S.charAt(i) - '0';
            f1 = Math.min(f0, f1 + 1 - (S.charAt(i) - '0'));
        }
        return f1;
    }


    public static void main(String[] args){
        System.out.println(new FlipStringToMonotoneIncreasing().minFlipMonoIncrDPSimple("11011"));
    }
}
