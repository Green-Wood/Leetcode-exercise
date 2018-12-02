package Exercises;
/*
给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。

update(i, val) 函数可以通过将下标为 i 的数值更新为 val，从而对数列进行修改。

示例:

Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
说明:

数组仅可以在 update 函数下进行修改。
你可以假设 update 函数与 sumRange 函数的调用次数是均匀分布的。



HINT: Segment Tree
 */
public class RangeSumQueryMutable {

    private class Node {
        int sum;
        int start, end;
        Node left, right;
        Node(int start, int end) {
            this.start = start;
            this.end = end;
            sum = 0;
            left = null;
            right = null;
        }
    }

    Node root;

    public RangeSumQueryMutable(int[] nums) {
        root = buildTree(nums, 0, nums.length - 1);
    }

    private Node buildTree(int[] nums, int start, int end) {
        if (start > end) return null;
        Node newNode = new Node(start, end);
        if (start == end) {
            newNode.sum = nums[start];
        } else {
            int mid = (end - start) / 2 + start;
            newNode.left = buildTree(nums, start, mid);
            newNode.right = buildTree(nums, mid + 1, end);
            newNode.sum = newNode.left.sum + newNode.right.sum;
        }
        return newNode;
    }

    public void update(int i, int val) {
        update(root, i, val);
    }

    private void update(Node x, int pos, int val) {
        if (x.start == x.end) {
            x.sum = val;
        } else {
            int mid = (x.end - x.start) / 2 + x.start;
            if (pos <= mid) {
                update(x.left, pos, val);
            } else {
                update(x.right, pos, val);
            }
            x.sum = x.left.sum + x.right.sum;
        }
    }

    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }

    private int sumRange(Node x, int start, int end) {
        if (x.start == start && x.end == end) {
            return x.sum;
        } else {
            int mid = (x.end - x.start) / 2 + x.start;
            if (end <= mid) {
                return sumRange(x.left, start, end);            //  目标范围全在左子树
            } else if (start > mid) {
                return sumRange(x.right, start, end);           // 目标范围全在右子树
            } else {
                return sumRange(x.left, start, mid) + sumRange(x.right, mid + 1, end);    // 目标范围跨越左右子树
            }
        }
    }

    public static void main(String[] args) {
        RangeSumQueryMutable query = new RangeSumQueryMutable(new int[]{1, 3, 5});
        System.out.println(query.sumRange(0, 2));
    }
}
