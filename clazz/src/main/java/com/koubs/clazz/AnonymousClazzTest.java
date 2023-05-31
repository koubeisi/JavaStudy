package com.koubs.clazz;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

/**
 * @author KouBeisi
 * @date
 */
@Slf4j
public class AnonymousClazzTest {

    @Test
    void test(){
        String str = "World";
        Consumer<String> consumer = s -> log.info(s + str);
        test();
        consumer.accept(str);

    }


}
