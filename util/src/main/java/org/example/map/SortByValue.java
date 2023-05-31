package org.example.map;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

public class SortByValue {

	public static void main(String[] args) {
		
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		map.put("Jim", 20);
		map.put("Xon", 10);
		map.put("Golm", 30);
		map.put("Vim", 70);
		map.put("Din", 40);
		map.put("Eiem", 60);
		
		//创建一个匿名内部类，Comparator的泛型必须和TreeMap的Key一致
		TreeMap<String,Integer> tree = new TreeMap<String,Integer>(new ValueComparator(map));		

		tree.putAll(map);
		
		for (Entry<String,Integer> entry : tree.entrySet()) {
			System.out.println(entry.getKey() + "-" + entry.getValue());
		}

	}
}

//a comparator that compares Strings
class ValueComparator implements Comparator<String>{

	HashMap<String, Integer> map = new HashMap<String, Integer>();

	public ValueComparator(HashMap<String, Integer> map){
		this.map.putAll(map);
	}

	@Override
	public int compare(String s1, String s2) {
		if(map.get(s1) >= map.get(s2)){
			return 1;
		}else{
			return -1;
		}	
	}
}
