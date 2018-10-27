/*
给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

说明: 叶子节点是指没有子节点的节点。

示例:
给定如下二叉树，以及目标和 sum = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
返回:

[
   [5,4,11,2],
   [5,8,4,5]
]
 */
package Tree;


import java.util.ArrayList;
import java.util.List;

public class PathSum2 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> finalList = new ArrayList<>();
        if (root == null) return finalList;
        search(root, finalList, new ArrayList<>(), 0, sum);
        return finalList;
    }

    private void search(TreeNode node, List<List<Integer>> finalList, List<Integer> list, int addNow, int target){
        addNow += node.val;
        list.add(node.val);
        if (node.right == null && node.left == null){
            if (addNow == target){
                finalList.add(new ArrayList<>(list));
            }
        } else {
            if (node.left != null) search(node.left, finalList, list, addNow, target);
            if (node.right != null) search(node.right, finalList, list, addNow, target);
        }
        list.remove(list.size() - 1);
    }
}
