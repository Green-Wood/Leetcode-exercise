package Array;

import java.util.Arrays;
import java.util.List;
/*
给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。

示例:

输入: [5,2,6,1]
输出: [2,1,1,0]
解释:
5 的右侧有 2 个更小的元素 (2 和 1).
2 的右侧仅有 1 个更小的元素 (1).
6 的右侧有 1 个更小的元素 (1).
1 的右侧有 0 个更小的元素.


HINT: 实质为逆序类题目，都可以用BST和MergeSort两种方法进行求解（详见ImportantReverse和NumberReverse）
 */
public class CountSmallerNumberAfterSelf {
    private class Node{
        int val, less, equal;    // less为比次节点的val小的数字的数目，equal为和此节点同样大小的数字的数目
        Node right, left;
        Node(int val) {
            this.val = val;
            equal = 1;
            less = 0;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        Integer[] counts = new Integer[nums.length];
        Node root = null;
        for (int i = nums.length-1; i >= 0; i--) {
            counts[i] = search(root, nums[i]);
            root = insert(root, nums[i]);
        }
        return Arrays.asList(counts);
    }

    private int search(Node x, int val) {
        if (x == null) return 0;
        if (val > x.val) {
            return x.less + x.equal + search(x.right, val);
        } else if (val < x.val) {
            return search(x.left, val);
        } else {
            return x.less;
        }
    }

    private Node insert(Node x, int val) {
        if (x == null) {
            x = new Node(val);
        }
        else if (val > x.val) {
            x.right = insert(x.right, val);
        } else if (val < x.val) {
            x.less++;
            x.left = insert(x.left, val);
        } else {
            x.equal++;
        }
        return x;
    }

    public static void main(String[] args) {
        int[] nums = {-1, -1};
        System.out.println(new CountSmallerNumberAfterSelf().countSmaller(nums));
    }
}
