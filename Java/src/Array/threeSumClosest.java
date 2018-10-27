package Array;

/*
给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。
返回这三个数的和。假定每组输入只存在唯一答案。

例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.

与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */
import java.util.Arrays;

public class threeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        int ans = 0;
        int delta = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++){
            if (i == 0 || nums[i] != nums[i-1]){
                int lo = i + 1, hi = nums.length-1;
                while (lo < hi){
                    int currAns = nums[i]+nums[lo]+nums[hi];
                    if (Math.abs(currAns-target) < delta){
                        delta = Math.abs(currAns - target);
                        ans = currAns;
                    }
                    if (currAns > target){
                        while (lo < hi && nums[hi] == nums[hi-1]) hi--;
                        hi--;
                    } else {
                        while (lo < hi && nums[lo] == nums[lo+1]) lo++;
                        lo++;
                    }
                }
            }
        }
        return ans;
    }

    // 还可以通过不断扩大误差，直到找到一个合适的threeSum为止

    public static void main(String[] args){
        System.out.println(new threeSumClosest().threeSumClosest(new int[]{-1, 2, 1,-4}, 3));
    }
}
