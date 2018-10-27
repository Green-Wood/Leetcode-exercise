package DP;

/*
    单词拆分：给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
    布尔数组dp[i]为true表示，字符串i之前的字符被成功归到单词词典中
 */

import java.util.List;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++){
            for (int j = 0; j < i; j++){
                if (dp[j] && wordDict.contains(s.substring(j, i))){
                    dp[i] = true;
                }
            }
        }
        return dp[s.length()];
    }
}
