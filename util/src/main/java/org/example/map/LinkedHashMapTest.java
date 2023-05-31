package org.example.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
 
public class LinkedHashMapTest {
	
    public static void main(String[] args) {
 
        House d1 = new House("red");
        House d2 = new House("black");
        House d3 = new House("white");
        House d4 = new House("white");
 
        LinkedHashMap<House,Integer> linkedHashMap = new LinkedHashMap<House,Integer>();
        linkedHashMap.put(d1, 10);
        linkedHashMap.put(d2, 15);
        linkedHashMap.put(d3, 5);
        linkedHashMap.put(d4, 20);
        
        System.out.println("使用LinkedHashMap输出");
        for (Entry<House,Integer> entry : linkedHashMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }   
        
        HashMap<House,Integer> hashMap = new HashMap<House,Integer>();
        hashMap.put(d1, 10);
        hashMap.put(d2, 15);
        hashMap.put(d3, 5);
        hashMap.put(d4, 20);
        
        System.out.println("使用HashMap输出");
        for (Entry<House,Integer> entry : hashMap.entrySet()) {
        	System.out.println(entry.getKey() + " - " + entry.getValue());
		}
    }
}

class House {
	
    private String color;
 
    House(String c) {
        color = c;
    }
 
    @Override
    public boolean equals(Object o) {
        return ((House) o).color.equals(this.color);
    }
 
    @Override
    public int hashCode() {
        return color.hashCode();
    }
 
    @Override
    public String toString(){
        return color + " house";
    }
}
