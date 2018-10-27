package Tree;

public class TreeSum {
    int target;
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        target = sum;
        return seek(root, 0);
    }

    private boolean seek(TreeNode node, int currSum){
        if (node == null) return false;
        currSum = currSum + node.val;
        if (node.right == null && node.left == null) {
            return currSum == target;
        }
        return seek(node.left, currSum) || seek(node.right, currSum);
    }
}
