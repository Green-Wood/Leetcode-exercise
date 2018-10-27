package Tree;

import java.util.ArrayList;

public class maxDepth {
    public int getMaxDepth(TreeNode root){
        return getDepth_First(root);
    }

    private int getDepth_First(TreeNode node){
        if (node == null){
            return 0;
        }
        if (node.left == null || node.right == null){
            return 1;
        }
        int maxDepthLeft = getDepth_First(node.left);
        int maxDepthRight = getDepth_First(node.right);
        return Math.max(maxDepthLeft, maxDepthRight) + 1;
    }

    int N;
    ArrayList<Integer> list = new ArrayList<>();              // 用list保存所有的深度，最后寻找最大的
    private void getDepth_Second(TreeNode node){
        if (node != null){
            N++;
            getDepth_Second(node.left);
            getDepth_Second(node.right);
            N--;
        }
        else {
            list.add(N);
        }
    }
}
