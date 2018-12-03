package Exercises.MaxSquare;
/*

No.42(CN)

给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。


上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。

示例:

输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        int lo = 0, hi = height.length - 1;
        int ans = 0;
        // Move lo and hi to the edge which can hold water
        while (lo < hi && height[lo] <= height[lo + 1]) lo++;
        while (lo < hi && height[hi] <= height[hi - 1]) hi--;

        while (lo < hi) {
            int top;
            // add volume if lo is shorter
            if (height[lo] < height[hi]) {
                top = height[lo];
                while (lo < hi && height[++lo] <= top) {
                    ans += top - height[lo];
                }
            } else {       // add volume if hi is shorter
                top = height[hi];
                while (lo < hi && height[--hi] <= top) {
                    ans += top - height[hi];
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new TrappingRainWater().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
