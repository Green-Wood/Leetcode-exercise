package StructureDesigning;

/*
实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。

调用 next() 将返回二叉搜索树中的下一个最小的数。

注意: next() 和hasNext() 操作的时间复杂度是O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 */

import Tree.TreeNode;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BSTIterator {
    Queue<Integer> queue;
    public BSTIterator(TreeNode root) {
        queue = new LinkedList<>();
        buildQueue(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        return queue.poll();
    }

    private void buildQueue(TreeNode node){
        if (node == null) return;
        buildQueue(node.left);
        queue.add(node.val);
        buildQueue(node.right);
    }

    public static void main(String[] args){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(5);
        queue.add(1);
        queue.add(3);
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */