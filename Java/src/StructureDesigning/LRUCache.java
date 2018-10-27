package StructureDesigning;

import java.util.HashMap;
import java.util.Map;

/*
运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。

获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。

进阶:

你是否可以在 O(1) 时间复杂度内完成这两种操作？
 */
public class LRUCache {
    private class Node{
        private int key;
        private int val;
        private Node pre, post;
    }

    private Map<Integer, Node> cache;
    private int count;
    private int capacity;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        cache = new HashMap<>();
        head = new Node();
        tail = new Node();

        head.post = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node x = cache.get(key);
        if (x == null) return -1;
        else {
            moveToHead(x);
            return x.val;
        }
    }

    public void put(int key, int value) {
        Node x = cache.get(key);
        if (x == null){
            Node t = new Node();
            t.val = value;
            t.key = key;
            cache.put(key, t);
            addToHead(t);
            count++;
            if (count > capacity){
                Node remove = popTail();
                cache.remove(remove.key);
                count--;
            }
        } else {
            x.val = value;
            moveToHead(x);
        }
    }

    private Node popTail(){
        Node x = tail.pre;
        deleteNode(x);
        return x;
    }

    private void moveToHead(Node x){
        deleteNode(x);
        addToHead(x);
    }

    private void deleteNode(Node x){
        Node pre = x.pre;
        Node post = x.post;

        pre.post = post;
        post.pre = pre;
    }

    private void addToHead(Node x){
        Node right = head.post;
        x.post = right;
        right.pre = x;

        head.post = x;
        x.pre = head;
    }
}
