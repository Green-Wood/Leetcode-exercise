package Array;

import java.util.*;
/*
假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。条件：

第 i 位的数字能被 i 整除
i 能被第 i 位上的数字整除
现在给定一个整数 N，请问可以构造多少个优美的排列？

示例1:

输入: 2
输出: 2
解释:

第 1 个优美的排列是 [1, 2]:
  第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
  第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除

第 2 个优美的排列是 [2, 1]:
  第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
  第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
 */
public class BeautifulArrangement {

    List<List<Integer>> l;

    public int countArrangement(int N) {
        boolean[] used = new boolean[N+1];
        ArrayList<Integer> list = new ArrayList<>();
        l = new ArrayList<>();
        return helper(1, list, used);
    }

    private int helper(int i, ArrayList<Integer> list, boolean[] used) {
        if (i == used.length) {
            l.add((List<Integer>) list.clone());
            return 1;
        } else {
            int n = 0;
            for (int j = 1; j < used.length; j++) {
                if (!used[j]) {
                    if (j % i == 0 || i % j == 0) {        // 选择合适的数字进行深度优先搜索
                        list.add(j);
                        used[j] = true;
                        n += helper(i+1, list, used);
                        used[j] = false;
                        list.remove(list.size()-1);
                    }
                }
            }
            return n;
        }
    }

    public static void main(String[] args) {
        BeautifulArrangement solution = new BeautifulArrangement();
        int n = solution.countArrangement(11);
        List<List<Integer>> ans = solution.l;
        System.out.println("total: " + n);
        for (List<Integer> list: ans) {
            System.out.println(list);
        }
    }
}
