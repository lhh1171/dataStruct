package com.lhh1171.hash;

import java.util.Arrays;

/**
 * 散列表实现
 */
public class HashMap<K, V> {
    /**
     * 散列表默认长度
     */
    private static final int DEFAULT_INITAL_CAPACITY = 8;

    /**
     * 装载因子
     */
    private static final float LOAD_FACTOR = 0.75f;

    /**
     * 初始化散列表数组
     */
    private MyEntry<K, V>[] table;

    /**
     * 实际元素数量
     */
    private int size = 0;

    /**
     * 散列表索引数量
     */
    private int use = 0;

    public HashMap() {
        table = (MyEntry<K, V>[]) new MyEntry[DEFAULT_INITAL_CAPACITY];
    }

    static class MyEntry<K, V> {
        K key;

        V value;

        MyEntry<K, V> next;

        MyEntry(K key, V value, MyEntry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 新增
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        int index = hash(key);
        // 位置未被引用，创建哨兵节点
        if (table[index] == null) {
            table[index] = new MyEntry<>(null, null, null);
        }

        MyEntry<K, V> tmp = table[index];
        // 新增节点
        if (tmp.next == null) {
            tmp.next = new MyEntry<>(key, value, null);
            size++;
            use++;
            // 动态扩容（table.length * LOAD_FACTOR计算出的是默认达到扩容标准的一个当前数组的使用值）
            if (use >= table.length * LOAD_FACTOR) {
                resize();
            }
        }
        // 解决散列冲突，使用链表法
        else {
            do {
                tmp = tmp.next;
                // key相同，覆盖旧的数据
                if (tmp.key == key) {
                    tmp.value = value;
                    return;
                }
            } while (tmp.next != null);

            MyEntry<K, V> temp = table[index].next;
            table[index].next = new MyEntry<>(key, value, temp);
            size++;
        }
    }

    /**
     * 散列函数
     * <p>
     * 参考hashmap散列函数
     *
     * @param key
     * @return
     */
    private int hash(Object key) {
        int h = key.hashCode();
        //左移16位，除2^16
        //h^h/2^16,
        int h1=h >>> 16;
        int i = (h) ^ (h1);

        //h小于2^16，h1都为0
        //和0抑或还得自己
        /*
         *h  0000 0000 0000 0000 1111 1111 1111 1111
         *h1 0000 0000 0000 0000 0000 0000 0000 0000
         *i  0000 0000 0000 0000 1111 1111 1111 1111
         *  */

        //要是h大于2^16,那就只保留前16位
        /*
         *h  0000 0000 0000 0011 1111 1111 1111 1111
         *h1 0000 0000 0000 0000 0000 0000 0000 0011
         *i  0000 0000 0000 0000 0000 0000 0000 0011
         *
         *  */
        /*...........................*/
        /*
         *h  1111 1111 1111 1111 1111 1111 1111 1111
         *h1 0000 0000 0000 0000 1111 1111 1111 1111
         *i  0000 0000 0000 0000 1111 1111 1111 1111
         *
         *  */

        //正数
        /*0----->2^16-1*/
        //负数
        /*0----->2^16-1*/
        //使得i永远小于
        /*((h = key.hashCode()) ^ (h >>> 16))
        * 表示*/
        return (key == null) ? 0 : i% table.length;
    }




    /**
     * 扩容
     */
    private void resize() {
        MyEntry<K, V>[] oldTable = table;
        table = (MyEntry<K, V>[]) new MyEntry[table.length * 2];
        use = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] == null || oldTable[i].next == null) {
                continue;
            }
            MyEntry<K, V> e = oldTable[i];
            while (e.next != null) {
                e = e.next;
                int index = hash(e.key);
                if (table[index] == null) {
                    use++;
                    // 创建哨兵节点
                    table[index] = new MyEntry<>(null, null, null);
                }
                table[index].next = new MyEntry<>(e.key, e.value, table[index].next);
            }
        }
    }

    /**
     * 删除
     *
     * @param key
     */
    public void remove(K key) {
        int index = hash(key);
        MyEntry e = table[index];
        if (e == null || e.next == null) {
            return;
        }

        MyEntry pre;
        MyEntry<K, V> headNode = table[index];
        do {
            pre = e;
            e = e.next;
            if (key == e.key) {
                pre.next = e.next;
                size--;
                if (headNode.next == null) use--;
                return;
            }
        } while (e.next != null);
    }

    /**
     * 获取
     *
     * @param key
     * @return
     */
    public V get(K key) {
        int index = hash(key);
        MyEntry<K, V> e = table[index];
        if (e == null || e.next == null) {
            return null;
        }
        while (e.next != null) {
            e = e.next;
            if (key == e.key) {
                return e.value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "HashMap{" +
                "table=" + Arrays.toString(table) +
                ", size=" + size +
                ", use=" + use +
                '}';
    }

    public static void main(String[] args) {
//        int i=4;
//        i=i>>>1;
//        System.out.println(i);
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("1","1");
        System.out.println(hashMap.get("1"));
    }
}
