package Exercises;

public class MaxPQ<T extends Comparable> {
    private T[] array;
    private int size;
    public MaxPQ(int maxSize){
        array = (T[]) new Comparable[maxSize + 1];
        size = 0;
    }

    public void insert(T value){
        size++;
        array[size] = value;
        swim(size);
    }

    public T popMax(){
        T max = array[1];
        array[1] = array[size];
        size--;
        sink(1);
        return max;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private void sink(int k){
        while (2 * k <= size){
            int child = 2 * k;
            if (child < size && less(child, child + 1)) child++;
            if (less(child, k)) break;
            swap(k, child);
            k = child;
        }
    }

    private void swim(int k){
        while (k > 1 && less(k/2, k)){
            swap(k, k/2);
            k = k/2;
        }
    }

    private boolean less(int i, int j){
        return array[i].compareTo(array[j]) < 0;
    }

    private void swap(int i, int j){
        T swap = array[i];
        array[i] = array[j];
        array[j] = swap;
    }

    public static void main(String[] args){
        MaxPQ<Integer> pq = new MaxPQ<>(4);
        pq.insert(3);
        pq.insert(2);
        pq.insert(123);
        pq.insert(101);
        while (!pq.isEmpty()){
            System.out.println(pq.popMax());
        }
    }
}
