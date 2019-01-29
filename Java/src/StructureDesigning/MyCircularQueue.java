package StructureDesigning;

public class MyCircularQueue {

    private int[] data;
    private int front;
    private int rear;
    private int length;
    private int maxSize;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        data = new int[k];
        front = 0;
        rear = -1;
        length = 0;
        maxSize = k;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull()) return false;
        rear = (rear + 1) % maxSize;
        data[rear] = value;
        length++;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) return false;
        front = (front + 1) % maxSize;
        length--;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        return isEmpty() ? -1 : data[front];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        return isEmpty() ? -1 : data[rear];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return length == 0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return length == maxSize;
    }

    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为3

        circularQueue.enQueue(1);  // 返回true

        circularQueue.enQueue(2);  // 返回true

        circularQueue.enQueue(3);  // 返回true

        circularQueue.enQueue(4);  // 返回false,队列已满

        circularQueue.Rear();  // 返回3

        circularQueue.isFull();  // 返回true

        circularQueue.deQueue();  // 返回true

        circularQueue.enQueue(4);  // 返回true

        circularQueue.Rear();  // 返回4

    }
}
