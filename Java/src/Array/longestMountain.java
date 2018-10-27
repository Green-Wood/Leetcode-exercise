package Array;
/*
我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：

B.length >= 3
存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
（注意：B 可以是 A 的任意子数组，包括整个数组 A。）

给出一个整数数组 A，返回最长 “山脉” 的长度。

如果不含有 “山脉” 则返回 0。



示例 1：

输入：[2,1,4,7,3,2,5]
输出：5
解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
示例 2：

输入：[2,2,2]
输出：0
解释：不含 “山脉”。

HINT : 双数组
 */
public class longestMountain {
    public int longestMountain(int[] A) {
        int N = A.length;
        int[] up = new int[N];
        int[] down = new int[N];
        for (int i = N-2; i >= 0; i--){
            if (A[i] > A[i+1]) down[i] = down[i+1] + 1;
        }
        for (int i = 1; i < N; i++){
            if (A[i] > A[i-1]) up[i] = up[i-1] + 1;
        }
        int res = 0;
        for (int i = 0; i < N; i++){
            if (up[i] > 0 && down[i] > 0) res = Math.max(res, up[i]+down[i]+1);
        }
        return res;
    }

    public static void main(String[] args){
        System.out.println(new longestMountain().longestMountain(new int[]{1,2,1,4,7,3,2,5}));
    }
}
