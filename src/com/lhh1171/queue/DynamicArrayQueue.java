package com.lhh1171.queue;


public class DynamicArrayQueue {
  // 数组：items，数组大小：n
  private final int[] data;
  // head表示队头下标，tail表示队尾下标
  private int head = 0;
  private int tail = 0;

  // 申请一个大小为capacity的数组
  public DynamicArrayQueue(int capacity) {
    data = new int[capacity];
  }

  // 入队操作，将item放入队尾
  public boolean enqueue(int item) {
    // tail == n表示队列末尾没有空间了
    /*-------------------------关键---------------------------------*/
    if (tail == data.length) {
      // tail ==n && head==0，表示整个队列都占满了
      if (head == 0) return false;
      // 数据搬移
      for (int i = head; i < tail; ++i) {
        data[i-head] = data[i];
      }
      // 搬移完之后重新更新head和tail
      tail -= head;
      head = 0;
    }

    data[tail] = item;
    tail++;
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

  public void printAll() {
    for (int i = head; i < tail; ++i) {
      System.out.print(data[i] + " ");
    }
    System.out.println();
  }
}
