package com.lhh1171.linklist;

public class SinglyLinkedList {
    private SinglyLinkedListNode head = null;

    /*头插法*/
    public void insertArrayToHead(int[] values){
        for (int v:values){
            insertTail(v);
        }
    }

    public void insertToHead(int value) {
        SinglyLinkedListNode newNode =
                new SinglyLinkedListNode(value, null);
        insertToHead(newNode);
    }

    public void insertToHead(SinglyLinkedListNode newNode) {
        if (head != null) {
            newNode.next = head;
        }
        head = newNode;
    }


    // 检测环
    public static boolean checkCircle(SinglyLinkedListNode list) {
        if (list == null) return false;

        SinglyLinkedListNode fast = list.next;
        SinglyLinkedListNode slow = list;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (slow == fast) return true;
        }

        return false;
    }


    /*尾插法*/
    public void insertTail(int value){
        SinglyLinkedListNode newNode =
                new SinglyLinkedListNode(value, null);
        //空链表，可以插入新节点作为head，也可以不操作
        if (head == null){
            head = newNode;
        }else{
            SinglyLinkedListNode q = head;
            //遍历到最后插入，也可以弄一个位置参数
            while(q.next != null){
                q = q.next;
            }
            newNode.next = null;
            q.next = newNode;
        }
    }
    public void printAll() {
        SinglyLinkedListNode p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }
    public void printAll( SinglyLinkedListNode p) {
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    //链表翻转
    public SinglyLinkedListNode inverseLinkList() {
        SinglyLinkedListNode temp=head;
        SinglyLinkedListNode preNode=temp;
        SinglyLinkedListNode lastNode=temp;
        SinglyLinkedListNode res = null;
        if (head==null){
            return null;
        }else {
            while (temp!=null){
                if (res==null){
                    res=new SinglyLinkedListNode(temp.getData(),null);
                }else {
                    lastNode=new SinglyLinkedListNode(temp.getData(),null);
                    preNode=res;
                    lastNode.next=preNode;
                    res=lastNode;
                }
                temp=temp.next;
            }
        }
        return res;
    }

    public static class SinglyLinkedListNode {
        private int data;
        private SinglyLinkedListNode next;

        public SinglyLinkedListNode(int data,
                                    SinglyLinkedListNode next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public SinglyLinkedListNode getNext() {
            return next;
        }

        public void setData(int data) {
            this.data = data;
        }

        public void setNext(SinglyLinkedListNode next) {
            this.next = next;
        }
    }

    //　判断是否为回文(正这读反着读都一样)
    /*找到对称两点，
    偶数就是中间两个点，
    奇数就是中间一个点的左右两点，
    比较这俩点左右点是否都一样*/

    public static void main(String[] args) {
        SinglyLinkedList linkedList=new SinglyLinkedList();
        linkedList.insertArrayToHead(new int[]{1,2,3,4,5});
        linkedList.printAll();
        SinglyLinkedListNode reverse = linkedList.inverseLinkList();
        linkedList.printAll(reverse);
    }
}
