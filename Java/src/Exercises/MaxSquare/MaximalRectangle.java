package Exercises.MaxSquare;

import java.util.Arrays;
/*
LeetCode No: 85 (CN)

给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。

示例:

输入:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
输出: 6

相类似的题目：MaximalSquare
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int len = matrix[0].length;
        int[] height = new int[len];       // 保存到此行之前，j处的高度
        int[] left = new int[len];           // 保存到此行之前，j处相同高度能够延伸的最左端
        int[] right = new int[len];         // 保存到此行之前，j处相同高度能够延伸的最右端+1
        Arrays.fill(right, len);
        int ans = 0;
        // 对从上到下每行进行DP，每次选出此行以上最大的矩形
        for (int i = 0; i < matrix.length; i++) {
            int currLeft = 0, currRight = len;        // 此行中，j处能够延伸的最左端和最右端
            for (int j = 0; j < len; j++) {
                if (matrix[i][j] == '1') height[j]++;
                else height[j] = 0;
            }
            for (int j = 0; j < len; j++) {         // 自左向右进行迭代
                if (matrix[i][j] == '1') left[j] = Math.max(left[j], currLeft);
                else {
                    left[j] = 0;
                    currLeft = j + 1;
                }
            }
            for (int j = len - 1; j >= 0; j--) {          // 自右向左进行迭代
                if (matrix[i][j] == '1') right[j] = Math.min(right[j], currRight);
                else {
                    right[j] = len;
                    currRight = j;
                }
            }
            for (int j = 0; j < len; j++) {
                ans = Math.max(ans, (right[j] - left[j]) * height[j]);       //计算此行以上最大的矩阵面积
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'},
        };
        System.out.println(new MaximalRectangle().maximalRectangle(matrix));
    }

/*
参见：

[
   ["1","0","1","0","0"],
   ["1","0","1","1","1"],
   ["1","1","1","1","1"],
   ["1","0","0","1","0"]
 ]
策略: 把matrix看成多个直方图, 每一行及其上方的数据都构成一个直方图, 需要考察matrix.size()个直方图
对于每个点(row, col), 我们最后都计算以这个点上方的连续的'1'往left, right方向延申可以得到的最大的矩形的面积
通过这种方法获取的矩形一定会把最大的矩形包含在内
height[row][col]记录的是(row, col)这个坐标为底座的直方图柱子的高度, 如果这个点是'0', 那么高度当然是0了
left[row][col]记录的是(row, col)这个坐标点对应的height可以延申到的最左边的位置
right[row][col]记录的是(row, col)这个坐标点对应的height可以延申到的最右边的位置+1
以上面的matrix为例,
对于(row=2, col=1)这个点, left=0, right=5, height=1
对于(row=2, col=2)这个点, left=2, right=3, height=3
(2,2)这个点与(2,1)紧挨着,left和right却已经变化如此之大了, 这是因为left和right除了受左右两边的'1'影响, 还受到了其上方连续的'1'的制约
由于点(2,2)上有height=3个'1', 这几个'1'的left的最大值作为当前点的left, 这几个'1'的right的最小值作为当前点的right
因此, 实际上, 我们是要找以hight对应的这条线段往左右两边移动(只能往全是'1'的地方移动), 可以扫过的最大面积
当hight与目标最大矩形区域的最短的height重合时, 最大矩形的面积就找到了, 如上面的例子, 就是点(2,3)或(2,4)对应的height
 */
}
