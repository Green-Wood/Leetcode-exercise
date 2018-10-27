package Exercises;

/*
编写一个算法来判断一个数是不是“快乐数”。

一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。
如果可以变为 1，那么这个数就是快乐数。

示例:

输入: 19
输出: true
解释:
1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1


需要知道的数学知识：快乐数
快乐数在进行计算的过程之后可以变为1
所有不快乐数的数字平方和计算，最后都会进入 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4 的循环中。

因此，我们需要做的是判断是否会存在这样一个圈
Floyd cycle detection 算法
如果有限状态机、迭代函数或者链表上存在环，那么在某个环上以不同速度前进的2个指针必定会在某个时刻相遇。
同时显然地，如果从同一个起点(即使这个起点不在某个环上)同时开始以不同速度前进的2个指针最终相遇，那么可以判定存在一个环，且可以求出2者相遇处所在的环的起点与长度。
 */


public class HappyNumber {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        do {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(fast);
            fast = digitSquareSum(fast);
        } while (slow != fast);
        return slow == 1;
    }

    private int digitSquareSum(int n){
        int sum = 0;
        int temp;
        while (n > 0){
            temp = n % 10;
            sum += temp * temp;
            n /= 10;
        }
        return sum;
    }
}
