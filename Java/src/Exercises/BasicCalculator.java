package Exercises;

import java.util.ArrayDeque;
import java.util.Deque;

/*
实现一个基本的计算器来计算一个简单的字符串表达式的值。

字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。

示例 1:

输入: "1 + 1"
输出: 2
示例 2:

输入: " 2-1 + 2 "
输出: 3
示例 3:

输入: "(1+(4+5+2)-3)+(6+8)"
输出: 23
 */
public class BasicCalculator {
    public int calculate(String s) {
        int number = 0;
        int ans = 0;
        int sign = 1;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            } else if (c == '+') {
                ans += sign * number;
                sign = 1;
                number = 0;
            } else if (c == '-') {
                ans += sign * number;
                sign = -1;
                number = 0;
            } else if (c == '(') {
                stack.push(ans);
                stack.push(sign);
                ans = 0;
                sign = 1;
            } else if (c == ')') {
                ans += sign * number;
                number = 0;
                ans *= stack.pop();
                ans += stack.pop();
            }
        }
        if (number != 0) ans += sign * number;
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new BasicCalculator().calculate("(1+2-(3+4))"));
    }
}
