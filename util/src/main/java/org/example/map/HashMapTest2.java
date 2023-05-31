package org.example.map;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * HashMap 的 key 必须实现 equals 和 hash 方法
 * @author KouBeisi
 */
public class HashMapTest2 {
    public static void main(String[] args) {
        HashMap<Dog, Integer> hashMap = new HashMap<>(8);
        Dog d1 = new Dog("red");
        Dog d2 = new Dog("black");
        Dog d3 = new Dog("white");
        Dog d4 = new Dog("white");
 
        hashMap.put(d1, 10);
        hashMap.put(d2, 15);
        hashMap.put(d3, 5);
        hashMap.put(d4, 20);
 
        //print size
        System.out.println(hashMap.size());
 
        //loop HashMap
        for (Entry<Dog, Integer> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey().toString() + " - " + entry.getValue());
        }
    }
}

class Dog {
    String color;

    Dog(String c) {
        color = c;
    }

    @Override
    public String toString(){
        return color + " dog";
    }

    @Override
    public int hashCode() {
        return this.color.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.color.equals( ((Dog)obj).color);
    }
}
