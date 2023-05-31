package com.example.lambda;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author KouBeisi
 * @since 2021/12/1
 */
public class StreamTest {

    @Test
    public void test(){

        List<String> list = List.of("Hello","World","你好","Good");

        final Stream<String> stringStream = list.stream().filter(str -> str.equals("Good"));

        final List<String> collect = stringStream.collect(Collectors.toList());

        System.out.println(collect);

    }

}
