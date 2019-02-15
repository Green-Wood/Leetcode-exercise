package Exercises.Others;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/*
Merge
给出一个区间的集合，请合并所有重叠的区间。

示例 1:

输入: [[1,3],[2,6],[8,10],[15,18]]
输出: [[1,6],[8,10],[15,18]]
解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
示例 2:

输入: [[1,4],[4,5]]
输出: [[1,5]]
解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。


Insert
给出一个无重叠的 ，按照区间起始端点排序的区间列表。

在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。

示例 1:

输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
输出: [[1,5],[6,9]]
示例 2:

输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
输出: [[1,2],[3,10],[12,16]]
解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 */
public class MergeAndInsertIntervals {

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

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1) return intervals;

        intervals.sort(Comparator.comparingInt(s -> s.start));

        List<Interval> ans = new ArrayList<>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;

        for (Interval interval: intervals) {
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

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        intervals.add(newInterval);
        return merge(intervals);
    }

    public static void main(String[] args){
        List<Interval> input = new ArrayList<>();
        input.add(new Interval(1, 2));
        input.add(new Interval(3, 5));
        input.add(new Interval(6, 7));
        input.add(new Interval(8, 10));
        input.add(new Interval(12, 16));
        List<Interval> output = new MergeAndInsertIntervals().insert(input, new Interval(4, 8));
        for (Interval interval: output){
            System.out.println("start: " + interval.start + " " + "end: " + interval.end);
        }
    }
}
