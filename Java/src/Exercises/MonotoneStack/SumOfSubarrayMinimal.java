package Exercises.MonotoneStack;

import java.util.ArrayDeque;
import java.util.Deque;

/*
给定一个整数数组 A，找到 min(B) 的总和，其中 B 的范围为 A 的每个（连续）子数组。

由于答案可能很大，因此返回答案模 10^9 + 7。



示例：

输入：[3,1,2,4]
输出：17
解释：
子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。


提示：

1 <= A <= 30000
1 <= A[i] <= 30000


HINT: Monotone Stack

mod运算的规律
(a + b) % p = (a % p + b % p) % p （1）
(a - b) % p = (a % p - b % p) % p （2）
(a * b) % p = (a % p * b % p) % p （3）

关于大于和绝对大于的使用
Intuition on the choice of strictly bigger (i.e. greater than, >) on the left and bigger
(i.e. greater than or equal, >=) on the right: We need to account for each subset exactly once.
If both sides use >=, then some contiguous subsets, specifically those including the minimum number A[i] multiple times,
will be accounted for more than once. Similarly, if both sides use >, then those subsets won't be accounted for at all.
 Therefore, either left or right side should use > and the other one should use >=。
 如：[4, 5, 2, 3, 5]
 其中有子集[5, 2, 3, 5]     如果两边都使用绝对大于 > ，则该子集就不会被考虑在内。而如果都使用大于等于 >= ，则该子集会被考虑两次

 */
public class SumOfSubarrayMinimal {
    public int sumSubarrayMins(int[] A) {
        Deque<int[]> PLE = new ArrayDeque<>();
        Deque<int[]> NLE = new ArrayDeque<>();
        int[] left = new int[A.length];
        int[] right = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            int count = 1;
            while (!PLE.isEmpty() && PLE.peek()[0] > A[i]) {
                count += PLE.poll()[1];
            }
            left[i] = count;
            PLE.push(new int[]{A[i], count});
        }
        for (int i = A.length - 1; i >= 0; i--) {
            int count = 1;
            while (!NLE.isEmpty() && NLE.peek()[0] >= A[i]) {     // 注意我们在left使用了绝对大于，而在这里使用大于等于
                count += NLE.poll()[1];
            }
            right[i] = count;
            NLE.push(new int[]{A[i], count});
        }
        int ans = 0;
        int MOD = (int) 1e9 + 7;
        for (int i = 0; i < A.length; i++) {
            ans  = (ans +(A[i] * left[i] * right[i]) % MOD) % MOD;           // 逐个取余数
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new SumOfSubarrayMinimal().sumSubarrayMins(new int[]{71,55,82,55}));
    }
}
