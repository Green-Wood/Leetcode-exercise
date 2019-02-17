package Exercises.Others;

import java.util.*;
/*
你有 k 个升序排列的整数数组。找到一个最小区间，使得 k 个列表中的每个列表至少有一个数包含在其中。

我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。

示例 1:

输入:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
输出: [20,24]
解释:
列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
注意:

给定的列表可能包含重复元素，所以在这里升序表示 >= 。
1 <= k <= 3500
-105 <= 元素的值 <= 105
对于使用Java的用户，请注意传入类型已修改为List<List<Integer>>。重置代码模板后可以看到这项改动。


HINT: 类似于Merge k sorted lists
 */
public class SmallestRange {
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> nums.get(o[0]).get(o[1])));
        int max = Integer.MIN_VALUE;                  // 记录当前区间中最大的那个元素
        for (int i = 0; i < nums.size(); i++) {
            pq.add(new int[]{i, 0});
            max = Math.max(max, nums.get(i).get(0));
        }
        int start = 0, end = Integer.MAX_VALUE;          // 记录当前区间的起点和终点
        while (pq.size() == nums.size()) {
            int[] curr = pq.poll();                      // 弹出最小的元素的坐标
            if (end - start > max - nums.get(curr[0]).get(curr[1])) {      // 更新最小区间
                start = nums.get(curr[0]).get(curr[1]);
                end = max;
            }
            if (curr[1] + 1 < nums.get(curr[0]).size()) {            // 更新下一个区间的最大值
                pq.add(new int[]{curr[0], curr[1] + 1});
                max = Math.max(max, nums.get(curr[0]).get(curr[1] + 1));
            }
        }
        return new int[]{start, end};
    }

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(4,10,15,24,26);
        List<Integer> list2 = Arrays.asList(0,9,12,20);
        List<Integer> list3 = Arrays.asList(5,18,22,30);
        List<List<Integer>> input = new ArrayList<>();
        input.add(list1);
        input.add(list2);
        input.add(list3);
        System.out.println(Arrays.toString(new SmallestRange().smallestRange(input)));
    }
}
