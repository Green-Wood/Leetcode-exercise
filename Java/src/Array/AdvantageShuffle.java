package Array;

import java.util.*;

/*
给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。

返回 A 的任意排列，使其相对于 B 的优势最大化。



示例 1：

输入：A = [2,7,11,15], B = [1,10,4,11]
输出：[2,11,7,15]
示例 2：

输入：A = [12,24,8,32], B = [13,25,32,11]
输出：[24,32,8,12]


思路：使用田忌赛马的想法，在A中选取刚好比B中大的数字，如果没有这样的数字，则使用A中最小的数字。
实现：TreeMap来记录当前A中最小的Entry，并选取刚好比B[i]大的Entry
 */
public class AdvantageShuffle {
    public int[] advantageCount(int[] A, int[] B) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int[] res = new int[B.length];
        for (int num: A){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int i = 0; i < B.length; i++){
            Map.Entry<Integer, Integer> entry = map.higherEntry(B[i]);
            if (entry == null){
                entry = map.firstEntry();
            }
            int key = entry.getKey();
            int val = entry.getValue();
            res[i] = key;
            if (val == 1){
                map.remove(key);
            } else {
                map.put(key, val-1);
            }
        }
        return res;
    }

    public static void main(String[] args){
        int[] arr = new AdvantageShuffle().advantageCount(new int[]{2, 7, 11, 15}, new int[]{1, 10, 4, 11});
        System.out.println(Arrays.toString(arr));
    }
}
