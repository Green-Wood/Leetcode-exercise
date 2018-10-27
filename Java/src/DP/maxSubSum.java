package DP;

/*
给定（可能有负数）整数序列A1, A2, A3..., An， 求这个序列中子序列和的最大值。
（为方便起见，如果所有整数均为负数，则最大子序列和为0）。
例如：输入整数序列： -2, 11, 8, -4, -1, 16, 5, 0，则输出答案为35，即从A2～A6。


HINT: 如果a[i]是负数，那么它不可能代表最优序列的起点，
因为任何包含a[i]的作为起点的子序列都可以通过使用a[i+1]作为起点得到改进。
类似的，任何负的子序列也不可能是最优子序列的前缀（原理相同）。
如果在内循环中检测到从a[i]到a[j]的子序列的和是负数，那么可以向后推进i。
关键的结论是：我们不仅能够把i推进到 i+1，而且实际上我们还可以把它一直推进到 j+1。

并非去找哪个子序列最大，而是排除那些不可能是最大的子序列

复杂度： n

 */

public class maxSubSum {
    public int sum(int[] array){
        int currSum = array[0];
        int maxSum = currSum;
        for (int i = 1; i < array.length; i++){
            if (currSum <= 0) currSum = array[i];   // 状态转移
            else currSum += array[i];
            maxSum = Math.max(maxSum, currSum);             // 每个循环更新最大值
        }
        return maxSum;
    }

    public static void main(String[] args){
        System.out.println(new maxSubSum().sum(new int[]{-2, 11, 8, -4, -1, 16, 5, 0}));
    }
}
