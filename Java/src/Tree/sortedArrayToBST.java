package Tree;

public class sortedArrayToBST {
    public TreeNode toBST(int[] nums){
        if (nums == null || nums.length == 0) return null;
        return addNode(nums, 0, nums.length - 1);
    }

    private TreeNode addNode(int[] nums, int lo, int hi){
        if (lo > hi) return null;
        int mid = (hi - lo) / 2 + lo;
        TreeNode x = new TreeNode(nums[mid]);
        x.left = addNode(nums, lo, mid - 1);
        x.right = addNode(nums, mid + 1, hi);
        return x;
    }
}
