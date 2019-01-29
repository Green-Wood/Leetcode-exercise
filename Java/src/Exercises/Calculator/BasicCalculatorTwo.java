package Exercises.Calculator;

import java.util.ArrayDeque;
import java.util.Deque;

/*
实现一个基本的计算器来计算一个简单的字符串表达式的值。

字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。

示例 1:

输入: "3+2*2"
输出: 7
示例 2:

输入: " 3/2 "
输出: 1
示例 3:

输入: " 3+5 / 2 "
输出: 5
 */
public class BasicCalculatorTwo {
    public int calculate(String s) {
        int number = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = 10 * number + (c - '0');
            }
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {   // 字符串最后一个字符为数字时，两个if都要进入
                if (sign == '+') {
                    stack.push(number);
                } else if (sign =='-') {
                    stack.push(-number);
                } else if (sign == '*') {
                    stack.push(number * stack.pop());
                } else if (sign == '/') {
                    stack.push(stack.pop() / number);
                }
                sign = c;
                number = 0;
            }
        }
        int ans = 0;
        for (int n: stack) {
            ans += n;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new BasicCalculatorTwo().calculate("3+2*2"));
    }
}
