package com.koubs.map;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author KouBeisi
 * @since 2025/1/29
 */

class LinkedHashMapTest {

    @Test
    void testLRU(){
        LRUCache<Integer, Integer> cache = new LRUCache<>(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        System.out.println(cache);
        cache.put(4, 4);
        System.out.println(cache);
        System.out.println(cache.get(2));
        System.out.println(cache);

    }

}

class LRUCache<K, V> extends LinkedHashMap<K, V>{

    private final int capacity;

    public LRUCache(int initialCapacity) {
        super(initialCapacity, 0.75f, true);
        this.capacity = initialCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return capacity < size();
    }
}
