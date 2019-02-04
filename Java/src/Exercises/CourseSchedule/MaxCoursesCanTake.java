package Exercises.CourseSchedule;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
这里有 n 门不同的在线课程，他们按从 1 到 n 编号。每一门课程有一定的持续上课时间（课程时间）t 以及关闭时间第 d 天。一门课要持续学习 t 天直到第 d 天时要完成，你将会从第 1 天开始。

给出 n 个在线课程用 (t, d) 对表示。你的任务是找出最多可以修几门课。



示例：

输入: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
输出: 3
解释:
这里一共有 4 门课程, 但是你最多可以修 3 门:
首先, 修第一门课时, 它要耗费 100 天，你会在第 100 天完成, 在第 101 天准备下门课。
第二, 修第三门课时, 它会耗费 1000 天，所以你将在第 1100 天的时候完成它, 以及在第 1101 天开始准备下门课程。
第三, 修第二门课时, 它会耗时 200 天，所以你将会在第 1300 天时完成它。
第四门课现在不能修，因为你将会在第 3300 天完成它，这已经超出了关闭日期。


提示:

整数 1 <= d, t, n <= 10,000 。
你不能同时修两门课程。


Solution：
考虑两个课程 (a, x), (b, y)，设课程的结束时间 x < y && a < x && b < y
1、若 (a + b) < x   则a、b两个课程无论怎么排都能够完成，能完成2个课程
2、若 (a + b) > x && (a + b) < y  && a < b    则把a放在b前面的话，能够完成2个课程，而把b放在a前面则只能完成1个课程
3、若 (a + b) > x && (a + b) < y && a > b     则把a放在b前面的话，能够完成2个课程，而把b放在a前面只能完成1个课程
4、若 (a + b) > y    则a、b两个课程无论怎么排都只能够完成1个课程

综上所述，我们应该优先考虑那些结束时间较早的课程，并把这些课程排在前面。因此我们一开始就将courses数组按照结束时间的大小进行排序，无论我们
最后取不取课程 (a, x) 我们都应该将其排在 (b, y)前面优先考虑。因此，这一步并不是贪婪策略。
而后续考虑是否取课程 (a, x)时，便出现了两种策略
1、递归法中使用了一个简单的dp，memo[i][time] = Math.max(taken, notTaken);，来判断是否应该取该课程i。
2、循环法中使用了一个贪婪策略，通过寻找之前选中的课程中耗时最长的那个，与课程i的耗时进行对比，来判断是否应该取课程i。

 */
public class MaxCoursesCanTake {
    public int recursive(int[][] courses) {                // TLE，可理解为另一种暴力搜索，按数组顺序比较take和notTake的所有情况。
        int n = courses.length;
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
        Integer[][] memo = new Integer[n][courses[n-1][1] + 1];
        return schedule(courses, 0, 0, memo);
    }

    private int schedule(int[][] courses, int i, int time, Integer[][] memo) {    // beat 0.00%
        if (i == courses.length) {
            return 0;
        }
        if (memo[i][time] != null) {
            return memo[i][time];
        }
        int taken = 0;
        if (time + courses[i][0] <= courses[i][1]) {
            taken = 1 + schedule(courses, i + 1, time + courses[i][0], memo);
        }
        int notTaken = schedule(courses, i + 1, time, memo);
        memo[i][time] = Math.max(taken, notTaken);
        return memo[i][time];
    }

    // 可理解为先将能够完成的课程都选中，当出现一个不能完成的课程时，我们试图放弃之前选中的最耗时的课程来完成这个课程。
    public int iterative(int[][] courses) {
        int n = courses.length;
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
        int count = 0;
        int time = 0;
        for (int i = 0; i < n; i++) {
            if (time + courses[i][0] <= courses[i][1]) {
                count++;
                time += courses[i][0];
            } else {
                int max_i = i;
                for (int j = 0; j < i; j++) {
                    if (courses[j][0] > courses[max_i][0]) {
                        max_i = j;
                    }
                }
                if (courses[max_i][0] > courses[i][0]) {
                    time -= courses[max_i][0] - courses[i][0];
                }
                courses[max_i][0] = -1;
            }
        }
        return count;
    }

    public int priorityqueue(int[][] courses) {                    // 针对寻找max_i的过程，使用优先队列进行优化
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());    // 由大到小排列
        int time = 0;
        for (int[] c: courses) {
            if (time + c[0] <= c[1]) {         // 能够直接完成
                queue.add(c[0]);
                time += c[0];
            } else if (!queue.isEmpty() && queue.peek() > c[0]) {      // 通过放弃之前的最耗时课程来完成
                time -= queue.poll() - c[0];
                queue.add(c[0]);
            }
            //    不能够完成此课程，未选中
        }
        return queue.size();
    }
}
