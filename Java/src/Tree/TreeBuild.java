/*
由一棵树的前序遍历和中序遍历，构建出一棵二叉树
 */
package Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TreeBuild {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }

        TreeNode root = build(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, map);
        return root;
    }

    public TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> map){
        if (preStart > preEnd || inStart > inEnd) return null;

        TreeNode node = new TreeNode(preorder[preStart]);
        int nodeIndex = map.get(node.val);
        int numsLeft = nodeIndex - inStart;

        node.left = build(preorder, preStart+1, preStart+numsLeft, inorder, inStart, nodeIndex-1, map);
        node.right = build(preorder, preStart+numsLeft+1, preEnd, inorder, nodeIndex+1, inEnd, map);

        return node;
    }
}
