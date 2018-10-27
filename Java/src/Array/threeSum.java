package Array;

/*
给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。

例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]

HINT：遇到求xsum这种题目，都有套路的，就是要把它转换成sum-nums[i]到nums里去找存在不存在？
这题有3个数的sum，变成1+2sum，遍历一遍的过程中，当前有一个nums[i],还有2个，我们可以把
sums先sort，然后找从i往后找一个小的j以及从k=nums.length-1往前找一个，如果小了，j++，如果大了，k--
如果刚好找到了满足条件的，应该能保证三个相加==0
time:O(n^2)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class threeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i++){
            if (i == 0 || nums[i] != nums[i-1]){
                int lo = i+1, hi = nums.length-1;
                while (lo < hi){
                    if (nums[i]+nums[lo]+nums[hi] == 0){
                        res.add(Arrays.asList(nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo+1]) lo++;
                        while (lo < hi && nums[hi] == nums[hi-1]) hi--;
                        lo++;
                        hi--;
                    } else if (nums[i]+nums[lo]+nums[hi] > 0){
                        while (lo < hi && nums[hi] == nums[hi-1]) hi--;
                        hi--;
                    } else {
                        while (lo < hi && nums[lo] == nums[lo+1]) lo++;
                        lo++;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args){
        List<List<Integer>> list = new threeSum().threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        for (List<Integer> l: list){
            for (int i: l){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
