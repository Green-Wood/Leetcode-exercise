package Array;

import java.util.Arrays;
/*
实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须原地修改，只允许使用额外常数空间。

以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1


类似于NextGreatElement3
 */
public class NextPermutation {
    // My version
    public void nextPermutation(int[] nums) {
        int idx = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i+1]) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            Arrays.sort(nums);
        } else {
            int target;
            for (target = idx + 1; target < nums.length; target++) {
                if (nums[target] <= nums[idx]) {
                    break;
                }
            }
            target--;
            int swap = nums[target];
            nums[target] = nums[idx];
            nums[idx] = swap;
            Arrays.sort(nums, idx + 1, nums.length);
        }
    }

    // best practice

    public void bestPractice(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i > -1) {
            int j = nums.length - 1;
            while (j > i && nums[j] <= nums[i]) {
                j--;
            }
            swap(i, j, nums);
        }
        reverse(i + 1, nums.length - 1, nums);       // 后面的为降序排序，需要转化为升序
    }

    private void swap(int i, int j, int[] nums) {
        int swap = nums[i];
        nums[i] = nums[j];
        nums[j] = swap;
    }

    private void reverse(int i, int j, int[] nums) {
        while (i < j) {
            swap(i, j, nums);
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1};
        new NextPermutation().bestPractice(nums);
        System.out.println(Arrays.toString(nums));
    }
}
