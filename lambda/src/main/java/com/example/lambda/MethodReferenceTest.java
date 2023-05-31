package com.example.lambda;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author koubs
 * @date 2021/4/19
 */
@Slf4j
public class MethodReferenceTest {

    /**
     * 静态方法引用
     */
    void test(){
        Consumer<String> consumer = System.out::println;
       Function<Integer ,String> function = String::valueOf;
    }

    /**
     * 实例方法引用
     */
    void test1(){
        Consumer<String> consumer = log::info;

    }

    /**
     * 构造函数方法引用
     */
    void test2(){
        Function<String,String> function = String::new;
    }

}
