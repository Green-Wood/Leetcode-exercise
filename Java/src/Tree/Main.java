package Tree;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args){
        TreeNode root = new sortedArrayToBST().toBST(new int[]{-10,-3,0,5,9});
        System.out.println(root.left.right.val);
    }

    private static void printInLevel(TreeNode root){
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            System.out.print("n: " + queue.size() + " ");
            for (int n = queue.size(); n > 0; n--){
                TreeNode node = queue.pop();
                System.out.print(node.val + " ");
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            System.out.println();
        }
    }

    private static void printInDepth(TreeNode root){
        if (root == null) return;
        System.out.println(root.val);
        printInDepth(root.left);
        printInDepth(root.right);
    }
}
