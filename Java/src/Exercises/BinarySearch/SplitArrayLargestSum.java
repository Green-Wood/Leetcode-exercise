package Exercises.BinarySearch;
/*
给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。

注意:
数组长度 n 满足以下条件:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
示例:

输入:
nums = [7,2,5,10,8]
m = 2

输出:
18

解释:
一共有四种方法将nums分割为2个子数组。
其中最好的方式是将其分为[7,2,5] 和 [10,8]，
因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。


HINT:
 */
public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        long max = Integer.MIN_VALUE;
        long sum = 0;
        for (int n: nums) {
            max = Math.max(max, n);
            sum += n;
        }

        long lo = max;
        long hi = sum;
        while (lo < hi) {
            long mid = (hi - lo) / 2 + lo;
            if (!valid(nums, mid, m)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return (int) lo;
    }

    private boolean valid(int[] nums, long target, int m) {              // if valid, the target is too large
        int count = 0;
        int sum = 0;
        for (int n: nums) {
            sum += n;
            if (sum > target) {
                count++;
                sum = n;
                if (count >= m) return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(new SplitArrayLargestSum().splitArray(new int[]{7, 2, 5, 10, 8}, 2));
    }
}
