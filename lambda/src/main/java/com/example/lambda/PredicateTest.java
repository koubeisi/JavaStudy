package com.example.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 断言
 * @author koubs
 * @date 2021/4/19
 */
@Slf4j
public class PredicateTest {


    @Test
    public void predicate(){
        List<String> names = Arrays.asList("Adam", "Alexander", "John", "Tom");
        List<String> result = names.stream()
                // 此处使用 Predicate
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());
        result.forEach(log::info);
    }

    @Test
    public void predicateAnd(){
        Predicate<String> predicate = str -> str.startsWith("A");

        List<String> names = Arrays.asList("Adam", "Alexander", "John", "Tom");
        List<String> result = names.stream()
                // 此处使用 Predicate
                .filter(predicate.and(str -> str.endsWith("m")))
                .collect(Collectors.toList());
        result.forEach(log::info);
    }

    @Test
    public void predicateOr(){
        Predicate<String> predicate = str -> str.startsWith("A");

        List<String> names = Arrays.asList("Adam", "Alexander", "John", "Tom");
        List<String> result = names.stream()
                // 此处使用 Predicate
                .filter(predicate.or(str -> str.endsWith("m")))
                .collect(Collectors.toList());
        result.forEach(log::info);
    }

}
