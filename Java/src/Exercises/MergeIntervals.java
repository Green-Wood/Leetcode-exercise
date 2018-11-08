package Exercises;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/*
给出一个区间的集合，请合并所有重叠的区间。

示例 1:

输入: [[1,3],[2,6],[8,10],[15,18]]
输出: [[1,6],[8,10],[15,18]]
解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
示例 2:

输入: [[1,4],[4,5]]
输出: [[1,5]]
解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class MergeIntervals {
    static class Interval{
        int start;
        int end;
        Interval() {
            start = 0;
            end = 0;
        }
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public List<Interval> merge(List<Interval> intervals){
        if (intervals.size() <= 1) return intervals;

        intervals.sort(Comparator.comparingInt(s -> s.start));

        List<Interval> ans = new ArrayList<>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;

        for (Interval interval: intervals){
            if (interval.start <= end) {
                end = Math.max(end, interval.end);
            } else {
                ans.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        ans.add(new Interval(start, end));

        return ans;
    }

    public static void main(String[] args){
        List<Interval> input = new ArrayList<>();
        input.add(new Interval(1, 3));
        input.add(new Interval(2, 6));
        input.add(new Interval(8, 10));
        input.add(new Interval(15, 18));
        List<Interval> output = new MergeIntervals().merge(input);
        for (Interval interval: output){
            System.out.println("start: " + interval.start + " " + "end: " + interval.end);
        }
    }
}
