package Exercises;

public class MinStack {
    /*
    实现在常数时间内得到最小数的栈
    要点：在节点中加入一个min（int）
    保存此节点及其以后的节点的最小值
     */
    private class Node{
        Node next;
        int val;
        int min;
        Node(int val, int min, Node next){
            this.val = val;
            this.min = min;
            this.next = next;
        }
        Node(int val, int min){
            this(val, min, null);
        }
    }

    Node head;

    public MinStack() {
    }

    public void push(int x) {
        if (head == null){
            head = new Node(x, x);
        }
        else {
            head = new Node(x, Math.min(head.min, x), head);
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }
}
