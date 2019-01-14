package Exercises.BinarySearch;

import Tree.TreeNode;
/*
给出一个完全二叉树，求出该树的节点个数。

说明：

完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。

示例:

输入:
    1
   / \
  2   3
 / \  /
4  5 6

输出: 6

HINT: 通过右子树的高度来判断需要向左或是向右寻找。
 */
public class CountBSTNode {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int h = height(root);
        if (height(root.right) == h - 1) {
            return (1 << h - 1) + countNodes(root.right);
        } else {
            return (1 << h - 2) + countNodes(root.left);
        }
    }

    private int height(TreeNode root) {
        if (root == null) return 0;
        else return 1 + height(root.left);
    }
}
