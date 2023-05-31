package org.example.optional.constructor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Optional 的构造方式
 * @author koubs
 * @date 2021/4/4
 */
@Slf4j
public class ConstructorTest {

    /**
     * 通过 Optional 的静态方法 empty 构造空的 Optional 对象
     */
    @Test
    void empty(){
        final Optional<String> stringOptional = Optional.empty();
        final String str = stringOptional.orElse("");
        log.info("{}",str);
    }

    /**
     * 通过 Optional 的静态方法 of 构造空的 Optional 对象
     * of 方法不能传入 null 值
     */
    @ParameterizedTest
    @MethodSource("streamSource")
    void of(String str){
        final Optional<String> stringOptional = Optional.of(str);
        final String s = stringOptional.orElse("");
        log.info("{}",s);
    }

    /**
     * 通过 Optional 的静态方法 ofNullable 构造空的 Optional 对象
     * ofNullable 方法可以传入 null 值
     */
    @ParameterizedTest
    @MethodSource("streamSource")
    void ofNullable(String str){
        final Optional<String> optional = Optional.ofNullable(str);
        final String s = optional.orElse("");
        log.info("{}",s);
    }

    static Stream<String> streamSource(){
        return Stream.of("hello",null);
    }

}
