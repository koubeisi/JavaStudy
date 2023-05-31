package org.example.collection.list;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author koubs
 * @date 2021/4/11
 */
public class ArraysTest {

    /**
     * 使用工具类Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方法，它的add/remove/clear方法会抛出UnsupportedOperationException异常。
     *  说明：asList的返回对象是一个Arrays内部类，并没有实现集合的修改方法。Arrays.asList体现的是适配器模式，只是转换接口，后台的数据仍是数组。
     */
    @Test
    void test() {
        String[] str = new String[] { "you", "wu" };
        List<String> list = Arrays.asList(str);
        // 此处报 UnsupportedOperationException
        list.add("me");
    }
}
