package Tree;

import java.util.*;

public class LevelOrder {
    // 普通的层次遍历
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

    // 锯齿状层次遍历
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int flag = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                l.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
            if (flag % 2 != 0){
                Collections.reverse(l);
            }
            flag++;
            res.add(l);
        }
        return res;
    }
}
