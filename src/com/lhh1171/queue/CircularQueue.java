package com.lhh1171.queue;


public class CircularQueue {
  // 数组：items，数组大小：n
  private final int[] data;
  private int n = 0;
  // head表示队头下标，tail表示队尾下标
  private int head = 0;
  private int tail = 0;

  // 申请一个大小为capacity的数组
  public CircularQueue(int capacity) {
    data = new int[capacity];
    n = capacity;
  }

  // 入队
  public boolean enqueue(int item) {
    // 队列满了
    if ((tail + 1) % n == head) return false;
    data[tail] = item;
    tail = (tail + 1) % n;
    return true;
  }

  // 出队
  public int dequeue() {
    // 如果head == tail 表示队列为空
    if (head == tail) return -1;
    int ret = data[head];
    head = (head + 1) % n;
    return ret;
  }

  public void printAll() {
    if (0 == n) return;
    for (int i = head; i % n != tail; i = (i + 1) % n) {
      System.out.print(data[i] + " ");
    }
    System.out.println();
  }
}
