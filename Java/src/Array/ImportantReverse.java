package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。

你需要返回给定数组中的重要翻转对的数量。

示例 1:

输入: [1,3,2,3,1]
输出: 2
示例 2:

输入: [2,4,3,5,1]
输出: 3


相似的方法见：ReverseNumber
 */
public class ImportantReverse {
    private int count = 0;
    private int[] temp;

    public int reversePairs(int[] nums) {               // 基于MergeSort的实现
        temp = new int[nums.length];
        MergeSort(0, nums.length-1, nums);
        return count;
    }

    private void MergeSort(int lo, int hi, int[] nums){
        if (lo >= hi) return;
        int mid = (hi-lo)/2 + lo;
        MergeSort(lo, mid, nums);
        MergeSort(mid+1, hi, nums);

        int curr = 0;                                  // 给予两个排好序的数组，求逆序数，和ReverseNumber不同的count方法
        for (int i = lo, j = mid + 1; i <= mid;) {
            if (j > hi || (long) nums[i] <= 2 * nums[j]) {
                i++;
                count += curr;
            } else {
                j++;
                curr++;
            }
        }

        int i = lo, j = mid + 1;
        int t = lo;

        while (i <= mid && j <= hi){
            if (nums[i] <= nums[j]){
                temp[t++] = nums[i++];
            } else {
                temp[t++] = nums[j++];
            }
        }
        while (i <= mid) temp[t++] = nums[i++];
        while (j <= hi) temp[t++] = nums[j++];
        System.arraycopy(temp, lo, nums, lo, hi-lo+1);
    }


    private class Node{
        int val, cnt;         // cnt为大于等于此节点val的数量
        Node left, right;
        Node(int val) {
            this.val = val;
            this.cnt = 1;
        }
    }

    public int reversePairsBST(int[] nums) {         // 基于二叉搜索树的实现
        int ans = 0;                                // 缺点：由于输入流的不同，二叉树可能不平衡，导致搜索效率下降，TLE
        Node root = null;                           // 可以通过AVL或红黑树进行修正，但实现较为复杂
        for (int n: nums) {
            ans += search(root, 2 * (long) n + 1);
            root = insert(root, n);
        }
        return ans;
    }

    private int search(Node x, long val) {
        if (x == null) {
            return 0;
        }
        if (x.val > val) {
            return x.cnt + search(x.left, val);
        } else if (x.val < val) {
            return search(x.right, val);
        } else {
            return x.cnt;
        }
    }

    private Node insert(Node x, int val) {
        if (x == null) {
            x =  new Node(val);
        } else if (x.val > val) {
            x.left = insert(x.left, val);
        } else if (x.val < val) {
            x.cnt++;
            x.right = insert(x.right, val);
        } else {
            x.cnt++;
        }
        return x;
    }

    public static void main(String[] args) {
        System.out.println(new ImportantReverse().reversePairsBST(new int[]{1, 3, 2, 3, 1}));
    }
}
