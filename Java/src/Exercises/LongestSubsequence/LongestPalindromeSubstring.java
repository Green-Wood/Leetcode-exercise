package Exercises.LongestSubsequence;

/*
  寻找最长的回文子字符串
  状态转移：
    dp[j][i] == true if and only if (s[i] == s[j] and dp[j+1][i-1])
 */
public class LongestPalindromeSubstring {
    public String DPVersion(String s){
        if (s.length() == 0) return "";
        int start = 0;
        int end = 0;
        int max = 0;
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++){
            for (int j = 0; j <= i; j++){
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j+1][i-1])){        // 状态转移
                    dp[j][i] = true;
                }
                if (dp[j][i] && max < i - j + 1){
                    max = i - j + 1;
                    start = j;
                    end = i;
                }
            }
        }
        return s.substring(start, end+1);
    }


    public static void main(String[] args){
        System.out.println(new LongestPalindromeSubstring().DPVersion("babab"));
    }
}
