package Exercises.LongestSubsequence;

import java.util.ArrayDeque;
import java.util.Deque;

/*
给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。

示例 1:

输入: "(()"
输出: 2
解释: 最长有效括号子串为 "()"
示例 2:

输入: ")()())"
输出: 4
解释: 最长有效括号子串为 "()()"
 */
public class LongestValidParentheses {

    // Using stack
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        int left = -1;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
             if (s.charAt(i) == '(') {
                 stack.push(i);
             } else {
                 if (stack.isEmpty()) {
                     left = i;
                 } else {
                     stack.pop();
                     if (stack.isEmpty()) maxLen = Math.max(maxLen, i - left);     // 形如 "()()" 或 ")()" 右括号多于左括号
                     else maxLen = Math.max(maxLen, i - stack.peek());                  // 形如 "(()"  左括号多于右括号
                 }
             }
        }
        return maxLen;
    }

    // Using DP
    public int dpVersion(String s) {
        int[] dp = new int[s.length()];          // dp[i]表示在i处终止的最长有效括号长度
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')' && i >= 1) {
                if (s.charAt(i - 1) == '(') {                 // 形如 "()"
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else {
                    if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {        // "形如 ((()))"
                        dp[i] = dp[i - 1] + 2 + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0);
                    }
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    // two pointers O(1) space
    public int twoPointers(String s) {
        int left = 0, right = 0;                     // 计数左括号和右括号的个数
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {        // 从左向右扫描，保证左括号多于右括号
            if (s.charAt(i) == '(') left++;
            else right++;

            if (right > left) {
                left = right = 0;
            } else if (right == left) {
                maxLen = Math.max(maxLen, 2 * left);
            }
        }
        left = 0;
        right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {       // 从右向左扫描，保证右括号多余左括号
            if (s.charAt(i) == '(') left++;
            else right++;

            if (left > right) {
                left = right = 0;
            } else if (right == left) {
                maxLen = Math.max(maxLen, 2 * left);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(new LongestValidParentheses().longestValidParentheses("(()"));
    }
}
