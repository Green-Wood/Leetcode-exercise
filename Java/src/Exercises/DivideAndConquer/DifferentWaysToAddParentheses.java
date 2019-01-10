package Exercises.DivideAndConquer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

LeetCode No.241  NA

Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.

Example 1:

Input: "2-1-1"
Output: [0, 2]
Explanation:
((2-1)-1) = 0
(2-(1-1)) = 2
Example 2:

Input: "2*3-4*5"
Output: [-34, -14, -10, -10, 10]
Explanation:
(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10

HINT: Recursive, divide and conquer
 */

public class DifferentWaysToAddParentheses {                          // 4ms
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char curr = input.charAt(i);
            if (curr == '+' || curr == '-' || curr == '*') {
                String part1 = input.substring(0, i);
                String part2 = input.substring(i + 1);
                List<Integer> ret1 = diffWaysToCompute(part1);          // compute left part
                List<Integer> ret2 = diffWaysToCompute(part2);          // compute right part
                for (int a: ret1) {
                    for (int b: ret2) {
                        if (curr == '+') ret.add(a + b);
                        else if (curr == '-') ret.add(a - b);
                        else ret.add(a * b);
                    }
                }
            }
        }
        if (ret.size() == 0) {
            ret.add(Integer.parseInt(input));
        }
        return ret;
    }

    public List<Integer> solution2(String input) {                    // 3ms
        return solutionWithMemorization(input, new HashMap<>());
    }

    public List<Integer> solutionWithMemorization(String input, Map<String, List<Integer>> map) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char curr = input.charAt(i);
            if (curr == '+' || curr == '-' || curr == '*') {
                String part1 = input.substring(0, i);
                String part2 = input.substring(i + 1);
                List<Integer> ret1;
                List<Integer> ret2;
                if (map.containsKey(part1)) ret1 = map.get(part1);
                else ret1 = solutionWithMemorization(part1, map);
                if (map.containsKey(part2)) ret2 = map.get(part2);
                else ret2 = solutionWithMemorization(part2, map);
                for (int a: ret1) {
                    for (int b: ret2) {
                        if (curr == '+') ret.add(a + b);
                        else if (curr == '-') ret.add(a - b);
                        else ret.add(a * b);
                    }
                }
            }
        }
        if (ret.size() == 0) {
            ret.add(Integer.parseInt(input));
        }
        map.put(input, ret);
        return ret;
    }

    public static void main(String[] args) {
        String input = "2*3-4*5";
        List<Integer> list = new DifferentWaysToAddParentheses().diffWaysToCompute(input);
        System.out.println(list);
    }
}
