package StructureDesigning;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
实现一个数据结构支持以下操作：

Inc(key) - 插入一个新的值为 1 的 key。或者使一个存在的 key 增加一，保证 key 不为空字符串。
Dec(key) - 如果这个 key 的值是 1，那么把他从数据结构中移除掉。否者使一个存在的 key 值减一。如果这个 key 不存在，这个函数不做任何事情。key 保证不为空字符串。
GetMaxKey() - 返回 key 中值最大的任意一个。如果没有元素存在，返回一个空字符串""。
GetMinKey() - 返回 key 中值最小的任意一个。如果没有元素存在，返回一个空字符串""。
挑战：以 O(1) 的时间复杂度实现所有操作。
 */
public class AllOne {
    private class Node{
        int count;
        Node pre;
        Node next;
        Set<String> keySet;
        private Node(int count){
            this.count = count;
            keySet = new HashSet<>();
        }
    }

    private Node head;
    private Node tail;

    private Map<String, Integer> keyCountMap;
    private Map<Integer, Node> countNodeMap;

    /** Initialize your data structure here. */
    public AllOne() {
        head = new Node(Integer.MIN_VALUE);
        tail = new Node(Integer.MAX_VALUE);
        head.next = tail;
        tail.pre = head;
        keyCountMap = new HashMap<>();
        countNodeMap = new HashMap<>();
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (keyCountMap.containsKey(key)){
            changeCount(key, 1);
        } else {
            keyCountMap.put(key, 1);
            if (head.next.count != 1){
                addNodeAfter(head, new Node(1));
            }
            head.next.keySet.add(key);
            countNodeMap.put(1, head.next);
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (keyCountMap.containsKey(key)){
            int count = keyCountMap.get(key);
            if (count == 1){
                keyCountMap.remove(key);
                removeKeyFromNode(countNodeMap.get(count), key);
            } else {
                changeCount(key, -1);
            }
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (head.next == tail){
            return "";
        } else {
            return tail.pre.keySet.iterator().next();
        }
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (head.next == tail){
            return "";
        } else {
            return head.next.keySet.iterator().next();
        }
    }

    // increment can only be -1 or 1
    private void changeCount(String key, int increment){
        int count = keyCountMap.get(key);
        int toCount = count + increment;
        keyCountMap.put(key, toCount);
        Node currNode = countNodeMap.get(count);
        Node newNode;
        if (countNodeMap.containsKey(toCount)){
            newNode = countNodeMap.get(toCount);
        } else {
            newNode = new Node(toCount);
            countNodeMap.put(toCount, newNode);
            if (increment == 1){
                addNodeAfter(currNode, newNode);
            } else {
                addNodeAfter(currNode.pre, newNode);
            }
        }
        newNode.keySet.add(key);
        removeKeyFromNode(currNode, key);
    }

    private void addNodeAfter(Node target, Node newNode){
        newNode.pre = target;
        newNode.next = target.next;
        target.next.pre = newNode;
        target.next = newNode;
    }

    private void removeKeyFromNode(Node node, String key){
        node.keySet.remove(key);
        if (node.keySet.size() == 0){
            countNodeMap.remove(node.count);
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.next = null;
            node.pre = null;
        }
    }

    public static void main(String[] args){
        AllOne one = new AllOne();
        one.inc("a");
        one.inc("b");
        one.inc("b");
        one.inc("b");
        one.dec("b");
        one.dec("b");
        System.out.println(one.getMaxKey());
        System.out.println(one.getMinKey());
    }
}
