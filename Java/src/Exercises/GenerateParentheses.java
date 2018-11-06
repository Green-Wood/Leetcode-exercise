package Exercises;

import java.util.ArrayList;
import java.util.List;
/*
给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。

例如，给出 n = 3，生成结果为：

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

HINT：回溯法
 */
public class GenerateParentheses {
    public List<String> generate(int n){
        List<String> ans = new ArrayList<>();
        backTrack(ans, "", 0, 0, n);
        return ans;
    }

    private void backTrack(List<String> ans, String curr, int open, int close, int max){
        // open 为'('的数量，close为')'的数量
        if (curr.length() == max * 2){
            ans.add(curr);
            return;
        }

        if (open < max){
            backTrack(ans, curr + "(", open+1, close, max);
        }
        if (close < open){
            backTrack(ans, curr + ")", open, close+1, max);
        }
    }

    public static void main(String[] args){
        List<String> ans = new GenerateParentheses().generate(8);
        for (String s: ans){
            System.out.println(s);
        }
    }
}
