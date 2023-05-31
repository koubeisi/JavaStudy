package org.example.map;

import java.util.Map.Entry;
import java.util.TreeMap;

class Pencil implements Comparable<Pencil>{
    String color;
 
    Pencil(String c) {
        color = c;
    }
    @Override
    public boolean equals(Object o) {
        return ((Pencil) o).color.equals(this.color);
    }
    
    @Override
    public int hashCode() {
        return color.hashCode();
    }
    @Override
    public String toString(){   
        return color + " dog";
    }
    
	@Override
	public int compareTo(Pencil o) {
		return this.color.compareTo(o.color);
	}
}
 
/**
 * @author KouBeisi
 */
public class TreeMapTest {
    public static void main(String[] args) {
 
        Pencil d1 = new Pencil("red");
        Pencil d2 = new Pencil("black");
        Pencil d3 = new Pencil("white");
        Pencil d4 = new Pencil("white");
 
        TreeMap<Integer,Pencil> treeMap = new TreeMap<>();
        treeMap.put(10,d1 );
        treeMap.put(15,d2 );
        treeMap.put(5,d3 );
        treeMap.put(20,d4 );
 
        for (Entry<Integer,Pencil> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
