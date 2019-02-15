package Exercises.Others;

/*
给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

说明：你不能倾斜容器，且 n 的值至少为 2。

如果我们试图将指向较长线段的指针向内侧移动，矩形区域的面积将受限于较短的线段而不会获得任何增加。
但是，在同样的条件下，移动指向较短线段的指针尽管造成了矩形宽度的减小，但却可能会有助于面积的增大。

I have an alternative proof:

We starts with the widest container, l = 0 and r = n - 1.
Let's say the left one is shorter: h[l] < h[r].
Then, this is already the largest container the left one can possibly form.
There's no need to consider it again.
Therefore, we just throw it away and start again with l = 1 and r = n -1.


O(n)
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int max = 0;
        int i = 0, j = height.length - 1;
        while (i < j){
            max = Math.max(max, (j-i) * Math.min(height[i], height[j]));
            if (height[i] < height[j]){
                i++;
            } else {
                j--;
            }
        }
        return max;
    }
}
