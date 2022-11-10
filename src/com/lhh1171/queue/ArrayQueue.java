package com.lhh1171.queue;


//数组构建队列有缺点，当tail到达capacity之后，
// head接近tail时候，队列容量有限
// 所有需要构建动态队列DynamicArrayQueue
public class ArrayQueue {
    private final int[] data;
    // head表示队头下标，tail表示队尾下标
    private int head = 0;
    private int tail = 0;

    public ArrayQueue(int capacity) {
        data = new int[capacity];
    }

    // 入队
    public boolean enqueue(int item) {
        // 如果tail == n 表示队列已经满了
        if (tail == data.length) return false;
        data[tail] = item;
        ++tail;
        return true;
    }

    // 出队
    public int dequeue() {
        // 如果head == tail 表示队列为空
        if (head == tail) return -1;
        // 为了让其他语言的同学看的更加明确，把--操作放到单独一行来写了
        int ret = data[head];
        ++head;
        return ret;
    }

    public static void main(String[] args) {
        ArrayQueue arrayQueue=new ArrayQueue(10);
        for (int i = 1; i < 6; i++) {
            arrayQueue.enqueue(i);
        }
        for (int i = arrayQueue.head; i < arrayQueue.tail; ++i) {
            System.out.print(arrayQueue.data[i] + " ");
        }
        System.out.println();
    }

}
