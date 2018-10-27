package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
review一下某大神的ksum套路吧
管你是3sum，4sum还是5sum，999sum，最后都归结到2sum，666吧？全都是套路
time: n^k-1

双指针
 */
public class N_Sum {
    public List<List<Integer>> sum(int[] nums, int target, int n){
        Arrays.sort(nums);
        return N_Sum(nums, n, target, 0);
    }

    private List<List<Integer>> N_Sum(int[] nums, int n, int target, int index){
        List<List<Integer>> res = new ArrayList<>();
        if (n == 2){
            int lo = index, hi = nums.length-1;
            while (lo < hi){
                if (nums[lo]+nums[hi] == target){
                    List<Integer> list = new ArrayList<>();      // 这里不能使用Arrays.asList()，因为这个函数返回的不是标准List，不支持add()和remove()操作，会引起 UnsupportedOperationException
                    list.add(nums[lo]);
                    list.add(nums[hi]);
                    res.add(list);
                    while (lo < hi && nums[lo] == nums[lo+1]) lo++;
                    while (lo < hi && nums[hi] == nums[hi-1]) hi--;
                    lo++;
                    hi--;
                } else if (nums[lo]+nums[hi] > target){
                    while (lo < hi && nums[hi] == nums[hi-1]) hi--;
                    hi--;
                } else {
                    while (lo < hi && nums[lo] == nums[lo+1]) lo++;
                    lo++;
                }
            }
        } else {
            for (int i = index; i< nums.length-n+1; i++){
                List<List<Integer>> temp = N_Sum(nums, n-1, target-nums[i], i+1);
                for (List<Integer> l: temp){
                    l.add(0, nums[i]);
                }
                res.addAll(temp);
                while (i < nums.length-1 && nums[i] == nums[i+1]) i++;
            }
        }
        return res;
    }

    public static void main(String[] args){
        List<List<Integer>> list = new N_Sum().sum(new int[]{1, 0, -1, 0, -2, 2}, 0, 4);
        for (List<Integer> l: list){
            for (int n: l){
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }
}
