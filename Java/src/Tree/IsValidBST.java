package Tree;
public class IsValidBST {
    public boolean isValidBST(TreeNode root){
        return isValid(root, null, null);
    }

    private boolean isValid(TreeNode node, Integer maxVal, Integer minVal){
        if (node == null) return true;

        if (maxVal != null && node.val >= maxVal) return false;
        if (minVal != null && node.val <= minVal) return false;

        return isValid(node.left, node.val, minVal) && isValid(node.right, maxVal, node.val);
    }
}
