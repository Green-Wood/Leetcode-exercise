package Tree;
/*
给定一个非空二叉树，返回其最大路径和。

本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。

示例 1:

输入: [1,2,3]

       1
      / \
     2   3

输出: 6
示例 2:

输入: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

输出: 42

HINT:
A path from start to end, goes up on the tree for 0 or more steps,
then goes down for 0 or more steps. Once it
goes down, it can't go up. Each path has a highest node,
which is also the lowest common ancestor of all other nodes on the path.
不要关注于开始与结束的节点，而应该转换思路，对于每个节点，向左向右寻找最深的长度，组合成最长的路径。
 */
public class MaxPathNodeSum {
    // O(n)
    private int maxValue;
    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }

    /*
    1、更新可能的最长路径
    2、返回以当前节点开始的最大深度
     */
    private int maxPathDown(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(0, maxPathDown(root.left));
        int right = Math.max(0, maxPathDown(root.right));
        maxValue = Math.max(maxValue, left + right + root.val);
        return Math.max(left, right) + root.val;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(-1);
        root.right = new TreeNode(-3);
        System.out.println(new MaxPathNodeSum().maxPathSum(root));
    }
}
