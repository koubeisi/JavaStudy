package org.example.collection.set;

import java.util.TreeSet;

import org.junit.jupiter.api.Test;

/**
 * 用来测试TreeSet的用法
 * @author koubeisi
 * @date  2019年4月20日下午10:50:37
 * @version v1.0
 */
class TreeSetTest {

    @Test
    void test() {
        
        TreeSet<String> set=new TreeSet<>();
        
        set.add("aaaaa");
        set.add("abaaa");
        set.add("aacaa");
        set.add("aaa");
        set.add("Aaaaa");
        set.add("Baaaa");
        
        System.out.println(set.first());
        System.out.println(set.last());
        System.out.println(set.toString()); //输出数据
        System.out.println(set.toArray()); //输出地址
    }

}
