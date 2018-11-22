package Tree;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestInBST {

    private List<Integer> list;

    public int kthSmallest(TreeNode root, int k) {
        list = new ArrayList<>();
        helper(root);
        return list.get(k-1);
    }

    private void helper(TreeNode x) {
        if (x == null) return;
        helper(x.left);
        list.add(x.val);
        helper(x.right);
    }
}
