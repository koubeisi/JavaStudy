package com.example.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

/**
 * 输入一个，输出一个函数
 *
 * @author koubs
 * @date 2021/4/19
 */
@Slf4j
public class FunctionTest {

    /**
     * 级联表达式和柯里化
     * 柯里化：把多个参数的函数转换为只有一个参数的函数
     * 柯里化的目的：函数标准化
     * 高阶函数：返回函数的函数
     */
    @Test
    void test() {
        Function<Integer, Function<Integer, Integer>> fn2 = x -> y -> x * y;
        Integer integer = fn2.apply(2).apply(2);
        log.info("{}", integer);

        Function<String, Function<String, Function<String, String>>> fn3 = a -> b -> c -> a + b + c;
        String s = fn3.apply("I ").apply("Love ").apply("You");
        log.info(s);
    }

}
