package Tree;

import java.util.LinkedList;
import java.util.List;

public class LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root == null) return list;
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> temp = new LinkedList<>();
            for (int n = queue.size(); n > 0; n--){
                TreeNode node = queue.pop();
                temp.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            list.add(temp);
        }
        return list;
    }
}
