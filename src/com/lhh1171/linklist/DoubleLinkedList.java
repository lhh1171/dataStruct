package com.lhh1171.linklist;

public class DoubleLinkedList {
    private Node head = null;

    public void insertArrayToHead(int[] values){
        for (int v:values){
            insertToHead(v);
        }
    }

    public void insertToHead(int value) {
        Node newNode =
                new Node(value, null,null);
        insertToHead(newNode);
    }

    public void insertToHead(Node newNode) {
        if (head != null) {
            newNode.next = head;
            head.pre=newNode;
        }
        head=newNode;
    }

    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static class Node {
        private int data;
        private Node pre;
        private Node next;

        public Node(int data,Node next,Node pre) {
            this.data = data;
            this.next = next;
            this.pre  =pre;
        }

        public int getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public Node getPre() {
            return pre;
        }

        public void setPre(Node pre) {
            this.pre = pre;
        }

        public void setData(int data) {
            this.data = data;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList linkedList=new DoubleLinkedList();
        linkedList.insertArrayToHead(new int[]{1,2,3,4,5});
        linkedList.printAll();
    }
}
