package Exercises;

/*
给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。

示例：

输入: S = "ADOBECODEBANC", T = "ABC"
输出: "BANC"
说明：

如果 S 中不存这样的子串，则返回空字符串 ""。
如果 S 中存在这样的子串，我们保证它是唯一的答案。

最小子串的覆盖问题：For most substring problem, we are given a string and need to find a substring of it
which satisfy some restrictions. A general way is to use a hashmap assisted with two pointers.

 */
public class MinWindowSubstring {
    public String minWindow(String s, String t) {
        int[] map = new int[128];
        int i = 0, j = 0;
        int minLen = Integer.MAX_VALUE;
        int counter = t.length();
        int left = 0;
        for (int k = 0; k < t.length(); k++) {
            map[t.charAt(k) - 'A']++;
        }
        while (j < s.length()) {
            char curr = s.charAt(j++);
            if (map[curr - 'A']-- > 0) counter--;
            while (counter == 0) {
                if (minLen > j - i) {
                    minLen = j - i;
                    left = i;
                }
                char front = s.charAt(i++);
                if (map[front - 'A']++ == 0) counter++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(left, left + minLen);
    }

    public static void main(String[] args) {
        System.out.println(new MinWindowSubstring().minWindow("ADOBECODEBANC", "ABC"));
    }
}
