package StructureDesigning;

import java.util.TreeMap;
/*
Range 模块是跟踪数字范围的模块。你的任务是以一种有效的方式设计和实现以下接口。

addRange(int left, int right) 添加半开区间 [left, right)，跟踪该区间中的每个实数。添加与当前跟踪的数字部分重叠的区间时，应当添加在区间 [left, right) 中尚未跟踪的任何数字到该区间中。
queryRange(int left, int right) 只有在当前正在跟踪区间 [left, right) 中的每一个实数时，才返回 true。
removeRange(int left, int right) 停止跟踪区间 [left, right) 中当前正在跟踪的每个实数。


示例：

addRange(10, 20): null
removeRange(14, 16): null
queryRange(10, 14): true （区间 [10, 14) 中的每个数都正在被跟踪）
queryRange(13, 15): false （未跟踪区间 [13, 15) 中像 14, 14.03, 14.17 这样的数字）
queryRange(16, 17): true （尽管执行了删除操作，区间 [16, 17) 中的数字 16 仍然会被跟踪）


HINT: TreeMap
 */
public class RangeModule {
    private TreeMap<Integer, Integer> intervals;
    public RangeModule() {
        intervals = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        if (left >= right) return;
        Integer start = intervals.floorKey(left);
        Integer end = intervals.floorKey(right);
        if (start != null && intervals.get(start) >= left) {              // extend left of interval
            left = start;
        }
        if (end != null && intervals.get(end) > right) {                // extend right of interval
            right = intervals.get(end);
        }
        intervals.put(left, right);
        intervals.subMap(left, false, right, true).clear();      // remove overlap
    }

    public boolean queryRange(int left, int right) {
        Integer start = intervals.floorKey(left);
        if (start == null) return false;
        return intervals.get(start) >= right;
    }

    public void removeRange(int left, int right) {
        Integer start = intervals.floorKey(left);
        Integer end = intervals.floorKey(right);

        if (end != null && intervals.get(end) > right) {                   // sub right interval
            intervals.put(right, intervals.get(end));
        }
        if (start != null && intervals.get(start) > left) {                 // sub left interval
            intervals.put(start, left);                                     // we can't do this if statement first
        }

        intervals.subMap(left, true, right, false).clear();       // clear intermediate interval
    }

    public static void main(String[] args) {
        RangeModule module = new RangeModule();
        module.addRange(10, 20);
        module.removeRange(14, 16);
        System.out.println(module.queryRange(10, 14));
        System.out.println(module.queryRange(13, 15));
        System.out.println(module.queryRange(16, 17));
    }
}
