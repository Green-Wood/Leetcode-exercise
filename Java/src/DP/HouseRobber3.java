package DP;
/*
在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。

计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

示例 1:

输入: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \
     3   1

输出: 7
解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
示例 2:

输入: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \
 1   3   1

输出: 9
解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 */

import Tree.TreeNode;

public class HouseRobber3 {
    // 不使用额外空间来实现记忆化搜索
    public int rob(TreeNode root) {
        if (root == null) return 0;
        if (root.val < 0) return -root.val;            // 若root.val为负数，则之前就访问过该节点
        int robCurrent = root.val;
        if (root.left != null) robCurrent += rob(root.left.left) + rob(root.left.right);
        if (root.right != null) robCurrent += rob(root.right.left) + rob(root.right.right);
        int notRobCurrent = rob(root.left) + rob(root.right);
        root.val = -Math.max(robCurrent, notRobCurrent);
        return -root.val;
    }


    // 方法一rob(root)表示在root节点能得到的最大利益，robSub(root)则返回一个数组，记录了两个状态
    // 解决了方法一中重复访问子节点的问题，此方法只需访问两次，而前一种要访问六次
    public int robMemoState(TreeNode root) {     // res[0] 表示未选取root，res[1]表示选取了root
        int[] res = robSub(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robSub(TreeNode root) {
        if (root == null) return new int[2];
        int[] left = robSub(root.left);
        int[] right = robSub(root.right);

        int[] res = new int[2];
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];

        return res;
    }
}
