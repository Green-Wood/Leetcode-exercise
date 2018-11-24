package Array;

import java.util.Stack;

/*

84.

https://leetcode-cn.com/problems/largest-rectangle-in-histogram/


给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。

以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。

图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。



示例:

输入: [2,1,5,6,2,3]
输出: 10
 */
public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        return stack(heights);
    }

    // 分治算法，O(NlogN)，重点在于如何求出中间的矩形面积。
    private int divideAndConquer(int[] heights) {
        if (heights.length == 0) return 0;
        return maxInRange(heights, 0, heights.length-1);
    }

    private int maxInRange(int[] heights, int lo, int hi) {
        if (lo >= hi) return heights[lo];
        int mid = (lo + hi) / 2;
        int maxLeft = maxInRange(heights, lo, mid);
        int maxRight = maxInRange(heights, mid+1, hi);
        int maxCombine = 0;
        int h = Math.min(heights[mid], heights[mid+1]);
        int i = mid, j = mid + 1;
        while (i >= lo && j <= hi) {
            h = Math.min(h, Math.min(heights[i], heights[j]));
            maxCombine = Math.max(maxCombine, (j - i + 1) * h);
            if (i == lo) {
                j++;
            } else if (j == hi) {
                i--;
            } else {
                if (heights[i-1] > heights[j+1]) {
                    i--;
                } else {
                    j++;
                }
            }
        }
        return Math.max(maxCombine, Math.max(maxLeft, maxRight));
    }

    // 类比于maxRectangle里所使用的方法
    private int scannLeftAndRight(int[] heights) {
        if (heights.length == 0) return 0;
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        left[0] = 0;
        for (int i = 1; i < n; i++) {
            int currLeft = i - 1;
            while (currLeft >= 0 && heights[currLeft] >= heights[i]) {      // 向左搜寻，直到达到0或者找到一根高度比i处低的柱子
                currLeft = left[currLeft] - 1;
            }
            left[i] = currLeft + 1;
        }

        right[n-1] = n-1;
        for (int i = n-2; i >= 0; i--) {
            int currRight = i + 1;
            while (currRight < n && heights[currRight] >= heights[i]) {     // 向右搜寻，直到达到n-1或者找到一根高度比i处低的柱子
                currRight = right[currRight] + 1;
            }
            right[i] = currRight - 1;
        }

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            maxArea = Math.max(maxArea, heights[i] * (right[i] - left[i] + 1));
        }
        return maxArea;
    }

    private int stack(int[] heights) {
        int n = heights.length;
        int[] newHeights = new int[n+2];
        Stack<Integer> stack = new Stack<>();
        System.arraycopy(heights, 0, newHeights, 1, n);
        int maxArea = 0;
        for (int i = 0; i < n + 2; i++) {
            while (!stack.isEmpty() && newHeights[stack.peek()] > newHeights[i]) {// 遇到矮柱，就从右向左，将每个高柱的最大面积求出
                int top = stack.pop();
                int prev = stack.peek();
                maxArea = Math.max(maxArea, (i - prev - 1) * newHeights[top]);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(new LargestRectangleInHistogram().largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }
}
