package org.example.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author koubeisi
 * @date 2018年11月16日上午11:16:53
 * @version v1.0
 */
@Slf4j
class ArrayListTest {

    /**
     * 父串的改变会影响到字串
     */
	@ParameterizedTest
	@MethodSource("listSource")
	void subList01(ArrayList<String> list) {


		List<String> subList = list.subList(1, 3);
		log.info("{}", list);
		log.info("{}", subList);

        /*
         * the non-structural changes in the list are reflected in the subList
         */
		list.set(0, "#");
		log.info("list:{}",list);
		log.info("subList:{}",subList);

		/*
         * the structural changes in the list are not reflected in the subList
         */
		list.add("@");
		log.info("list:{}",list.toString());
		// 此处报 ConcurrentModificationException
		log.info("subList:{}",subList);
	}

	/**
	 * the structural changes in the sublist are reflected in the list
	 * 子串的改变会影响到父串已经另外的字串
	 */
	@ParameterizedTest
	@MethodSource("listSource")
	void subList02(List<String> list) {
		List<String> subList1 = list.subList(1, 3);
		List<String> subList2 = list.subList(3, 5);
		log.info("{}", subList1);
		log.info("{}", subList2);
		log.info("{}", list);


		subList1.add("$");
		subList1.set(0, "&");
		log.info("{}",subList1);
		// 此处报 ConcurrentModificationException
		log.info("{}",subList2);
		log.info("{}",list);
	}

	static Stream<List<String>> listSource(){
		return Stream.of(new ArrayList<>(Arrays.asList("0","1","2","3","4","5")));
	}
	
	/**
	 * How to remove a element from a list.
	 */
	@Test
	void test2() {
	    
	    List<String> list= new ArrayList<>();     
	    list.add("1");     
	    list.add("2");   
	    list.add("3");   
	    list.add("4");   
	    
	    log.info("Before remove...");

		list.removeIf("1"::equals);
/*
		Iterator<String> iterator = list.iterator();
		while(iterator.hasNext()){
			String item=iterator.next();
			if("1".equals(item)){
				iterator.remove();
			}
		}*/
	    
	    log.info("After remove...");
	    log.info(list.toString());
	}

}
