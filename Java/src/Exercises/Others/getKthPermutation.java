package Exercises.Others;

import java.util.ArrayList;
import java.util.List;

/*
给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。

按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：

"123"
"132"
"213"
"231"
"312"
"321"
给定 n 和 k，返回第 k 个排列。

说明：

给定 n 的范围是 [1, 9]。
给定 k 的范围是[1,  n!]。
示例 1:

输入: n = 3, k = 3
输出: "213"
示例 2:

输入: n = 4, k = 9
输出: "2314"
 */
public class getKthPermutation {
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        List<Integer> nums = new ArrayList<>();
        int product = 1;
        for (int i = 1; i <= n; i++){
            nums.add(i);
            product *= i;
        }
        k--;
        for (int i = n ;i > 0; i--){
            product /= i;
            int idx = k / product;
            k = k % product;
            sb.append(nums.get(idx));
            nums.remove(idx);
        }
        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println(new getKthPermutation().getPermutation(4, 9));
    }
}
