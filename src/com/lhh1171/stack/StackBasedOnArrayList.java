package com.lhh1171.stack;

public class StackBasedOnArrayList {
    private final int[] data;
    private int top = -1;

    public StackBasedOnArrayList(int capacity){
        data=new int[capacity];
    }

    public void push(int value) {
        // 判断是否栈空
        if (top == -1) {
            data[0] = value;
            top = 0;
        } else {
            data[top+1] = value;
            top++;
        }
    }

    public int pop(){
        if (top==-1){
            return -1;
        }else {
            int temp=top;
            top--;
            int tt=data[temp];
            data[temp]=0;
            return tt;
        }
    }

    public int[] getData() {
        return data;
    }

    public static void main(String[] args) {
        StackBasedOnArrayList stackBasedOnArrayList=
                new StackBasedOnArrayList(10);
        stackBasedOnArrayList.push(1);
        stackBasedOnArrayList.push(2);
        stackBasedOnArrayList.push(3);
        stackBasedOnArrayList.push(4);
        stackBasedOnArrayList.push(5);
        for (int i = 0; i < 5; i++) {
            System.out.println(stackBasedOnArrayList.data[i]);
        }

    }
}
