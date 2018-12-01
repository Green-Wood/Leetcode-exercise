package Array;

import java.util.Arrays;
/*
给定一个字符串 S 和一个字符 C。返回一个代表字符串 S 中每个字符到字符串 S 中的字符 C 的最短距离的数组。

示例 1:

输入: S = "loveleetcode", C = 'e'
输出: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]


HINT: 寻找每个字符与左边、右边最近的目标字符的距离
 */
public class ShortestCharDist {
    public int[] shortestToChar(String S, char C) {
        int n = S.length();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {              // 目标位置初始化为0，其他位置初始化为1
            if (S.charAt(i) == C) res[i] = 0;
            else res[i] = n;
        }
        // 类似于动态规划
        for (int i = 1; i < n; i++) {             // 左边
            res[i] = Math.min(res[i], res[i-1] + 1);
        }
        for (int i = n - 2; i >=0 ; i--) {        // 右边
            res[i] = Math.min(res[i], res[i+1] + 1);
        }
        return res;
    }

    private int[] shortestTochar2(String S, char C) {
        int n = S.length();
        int[] res = new int[n];
        int pos = -n;
        for (int i = 0; i < n; i++) {
            if (S.charAt(i) == C) {
                pos = i;
            }
            res[i] = i - pos;
        }
        pos = 2 * n;
        for (int i = n-1; i >= 0; i--) {
            if (S.charAt(i) == C) {
                pos = i;
            }
            res[i] = Math.min(res[i], pos - i);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ShortestCharDist().shortestToChar("loveleetcode", 'e')));
    }
}
