package DP;
/*
最初在一个记事本上只有一个字符 'A'。你每次可以对这个记事本进行两种操作：

Copy All (复制全部) : 你可以复制这个记事本中的所有字符(部分的复制是不允许的)。
Paste (粘贴) : 你可以粘贴你上一次复制的字符。
给定一个数字 n 。你需要使用最少的操作次数，在记事本中打印出恰好 n 个 'A'。输出能够打印出 n 个 'A' 的最少操作次数。

示例 1:

输入: 3
输出: 3
解释:
最初, 我们只有一个字符 'A'。
第 1 步, 我们使用 Copy All 操作。
第 2 步, 我们使用 Paste 操作来获得 'AA'。
第 3 步, 我们使用 Paste 操作来获得 'AAA'。
说明:

n 的取值范围是 [1, 1000] 。

 */
public class TwoKeysKeyboard {

    // dp version
    public int minSteps(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dp[i] = i;                               // for i is prime number
            int low = (int) Math.sqrt(i);
            for (int j = i - 1; j >= low; j--) {
                if (i % j == 0) {
                    dp[i] = dp[j] + (i / j);
                    break;
                }
            }
        }
        return dp[n];
    }

    // loop version
    public int loopVersion(int n) {
        int sum = 0;
        for (int divid = 2; divid <= n; divid++) {
            while (n % divid == 0) {
                sum += divid;
                n /= divid;
            }
        }
        return sum;
    }

    /*
    dp[i]为得到i个字符所需要的最少的步数。
    则dp[i]为： i的所有质因子之和，如：20 = 2 * 2 * 5， 则 dp[20] = 2 + 2 + 5 = 9
    下面来进行证明：
    对于dp[n]，可得到dp[n] = dp[n/j] + j (j为质数且j能够整除n)
    则我们可以将dp[n] 分解为 其所有的质因子之和
    比如：dp[11] = 11，因为11个字符只能通过一个个复制得到
    dp[100] = dp[50] + 2 = dp[25] + 2 + 2 = dp[5] + 5 + 2 + 2 = 5 + 5 + 2 + 2 = dp[10] + 2 + 5 = dp[20] + 5

    因此，对于本题，我们只需要求出参数n的 所有质因子 并全部相加即可。
     */

    public static void main(String[] args) {
        System.out.println(new TwoKeysKeyboard().minSteps(6));
    }
}
