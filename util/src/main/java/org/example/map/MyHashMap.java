package org.example.map;

/**
 * @video https://www.bilibili.com/video/av35049505?from=search&seid=17429949527721148628
 * @description HashMap的实现原理
 * @author koubeisi
 * @time 2019年4月3日下午5:37:59
 * @version v1.0
 * @param <K>
 * @param <V>
 */
public class MyHashMap<K, V> {

    private Entry<K, V>[] table;
    private final int CAPACITY = 8;
    private int size;

    /**
          * 存储键值对
     * @param key
     * @param value
     */
    public void put(K key, V value) {

        if (table == null) {
            inflate();
        }

        // 存Entry
        int hashCode = hash(key);
        int index = indexFor(hashCode);
        // 如果相等就更新
        for (Entry<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                entry.value = value;
            }
        }

        addEntry(key, value, index);
    }

    /*
          * 初始化数组
     */
    @SuppressWarnings("unchecked")
    private void inflate() {
        table = new Entry[CAPACITY];
    }

    /*
     * 计算key的hash值
     */
    private int hash(K key) {
        return key.hashCode();
    }

    /*
          * 根据hash值计算key的数组下标
     */
    private int indexFor(int hashCode) {
        return hashCode % table.length;
    }

    /*
     * 根据数组下标添加到指定位置
     */
    private void addEntry(K key, V value, int index) {
        Entry<K, V> entry = new Entry<K, V>(key, value, table[index]);
        table[index] = entry;
        size++;
    }

    public V get(K key) {
        int hashCode = hash(key);
        int index = indexFor(hashCode);
        for (Entry<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    static class Entry<K, V> {
        public K key;
        public V value;
        public Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    
    public static void main(String[] args) {
        MyHashMap<String,String> map = new MyHashMap<String,String>();
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");
        map.put("4", "value4");
        map.put("23", "value23");
        map.put("111", "value111");
        map.put("12", "value12");
        map.put("11", "value11");
        System.out.println(map.get("1"));
        System.out.println(map.get("2"));
        System.out.println(map.get("3"));
        System.out.println(map.get("4"));
        System.out.println("----------");
        map.put("1", "value0");
        System.out.println(map.get("1"));
        
    }

}
