package Exercises;

import java.util.Map;
import java.util.TreeMap;
/*

Leetcode No. 975 (CN)

给定一个整数数组 A，你可以从某一起始索引出发，跳跃一定次数。在你跳跃的过程中，第 1、3、5... 次跳跃称为奇数跳跃，而第 2、4、6... 次跳跃称为偶数跳跃。

你可以按以下方式从索引 i 向后跳转到索引 j（其中 i < j）：

在进行奇数跳跃时（如，第 1，3，5... 次跳跃），你将会跳到索引 j，使得 A[i] <= A[j]，A[j] 是可能的最小值。如果存在多个这样的索引 j，你只能跳到满足要求的最小索引 j 上。
在进行偶数跳跃时（如，第 2，4，6... 次跳跃），你将会跳到索引 j，使得 A[i] => A[j]，A[j] 是可能的最大值。如果存在多个这样的索引 j，你只能跳到满足要求的最小索引 j 上。
（对于某些索引 i，可能无法进行合乎要求的跳跃。）
如果从某一索引开始跳跃一定次数（可能是 0 次或多次），就可以到达数组的末尾（索引 A.length - 1），那么该索引就会被认为是好的起始索引。

返回好的起始索引的数量。



示例 1：

输入：[10,13,12,14,15]
输出：2
解释：
从起始索引 i = 0 出发，我们可以跳到 i = 2，（因为 A[2] 是 A[1]，A[2]，A[3]，A[4] 中大于或等于 A[0] 的最小值），然后我们就无法继续跳下去了。
从起始索引 i = 1 和 i = 2 出发，我们可以跳到 i = 3，然后我们就无法继续跳下去了。
从起始索引 i = 3 出发，我们可以跳到 i = 4，到达数组末尾。
从起始索引 i = 4 出发，我们已经到达数组末尾。
总之，我们可以从 2 个不同的起始索引（i = 3, i = 4）出发，通过一定数量的跳跃到达数组末尾。
示例 2：

输入：[2,3,1,1,4]
输出：3
解释：
从起始索引 i=0 出发，我们依次可以跳到 i = 1，i = 2，i = 3：

在我们的第一次跳跃（奇数）中，我们先跳到 i = 1，因为 A[1] 是（A[1]，A[2]，A[3]，A[4]）中大于或等于 A[0] 的最小值。

在我们的第二次跳跃（偶数）中，我们从 i = 1 跳到 i = 2，因为 A[2] 是（A[2]，A[3]，A[4]）中小于或等于 A[1] 的最大值。A[3] 也是最大的值，但 2 是一个较小的索引，所以我们只能跳到 i = 2，而不能跳到 i = 3。

在我们的第三次跳跃（奇数）中，我们从 i = 2 跳到 i = 3，因为 A[3] 是（A[3]，A[4]）中大于或等于 A[2] 的最小值。

我们不能从 i = 3 跳到 i = 4，所以起始索引 i = 0 不是好的起始索引。

类似地，我们可以推断：
从起始索引 i = 1 出发， 我们跳到 i = 4，这样我们就到达数组末尾。
从起始索引 i = 2 出发， 我们跳到 i = 3，然后我们就不能再跳了。
从起始索引 i = 3 出发， 我们跳到 i = 4，这样我们就到达数组末尾。
从起始索引 i = 4 出发，我们已经到达数组末尾。
总之，我们可以从 3 个不同的起始索引（i = 1, i = 3, i = 4）出发，通过一定数量的跳跃到达数组末尾。
示例 3：

输入：[5,1,3,4,2]
输出：3
解释：
我们可以从起始索引 1，2，4 出发到达数组末尾。


提示：

1 <= A.length <= 20000
0 <= A[i] < 100000
 */
public class OddEvenJump {
    // 暴力解法 O(N^3)
    public int oddEvenJumps(int[] A) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            boolean isOdd = true;
            int target = i;
            do {
                if (target == A.length - 1) count++;
                if (isOdd) target = findMin(A, target + 1);
                else target = findMax(A, target + 1);
                isOdd = !isOdd;
            } while (target < A.length);
        }
        return count;
    }

    private int findMin(int[] A, int index) {
        int value = A[index - 1];
        int minIndex = A.length;
        while (index < A.length) {
            if (A[index] >= value) {
                if (minIndex == A.length) {
                    minIndex = index;
                } else if (A[index] < A[minIndex]) {
                    minIndex = index;
                }
            }
            index++;
        }
        return minIndex;
    }

    private int findMax(int[] A, int index) {
        int value = A[index - 1];
        int maxIndex = A.length;
        while (index < A.length) {
            if (A[index] <= value) {
                if (maxIndex == A.length) {
                    maxIndex = index;
                } else if (A[index] > A[maxIndex]) {
                    maxIndex = index;
                }
            }
            index++;
        }
        return maxIndex;
    }

    //DP idea, using treeMap O(NlogN)
    /*
    可以把问题看成在i处向更高的值走（奇），或者向更低的值走（偶）
    high[i]表示，能否在i处通过向高处走来到达终点
    low[i]表示，能否在i处通过向低处走来到达终点

    考虑数组[5, 1, 3, 4, 2]
    1、若我们在2处，则 high(2) = true, low(2) = true 因为我们已经到达了终点
    2、若我们在4处，则 high(4) = false 因为向后没有比4更大的数，low(4) = high(2) = true
    3、若我们在3处，则 high(3) = low(4) = true, low(3) = high(2) = true
    4、若我们在1处，则 high(1) = low(2) = true, low(1) = false
    5、若我们在5处，则 high(5) = false, low(5) = high(4) = true

    我们将统计所有high(i) = true 的个数。
     */
    public int DPTreeMap(int[] A) {
        int count = 0;
        int n = A.length;
        boolean[] high = new boolean[n];
        boolean[] low = new boolean[n];
        high[n-1] = low[n-1] = true;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(A[n-1], n-1);
        for (int i = n-2; i >= 0; i--) {
            Map.Entry<Integer, Integer> hi = map.ceilingEntry(A[i]);
            Map.Entry<Integer, Integer> lo = map.floorEntry(A[i]);
            if (hi != null) high[i] = low[hi.getValue()];
            if (lo != null) low[i] = high[lo.getValue()];
            if (high[i]) count++;
            map.put(A[i], i);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new OddEvenJump().oddEvenJumps(new int[]{5,1,3,4,2}));
    }
}
