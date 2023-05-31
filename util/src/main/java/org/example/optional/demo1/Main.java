package org.example.optional.demo1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author koubs
 * @date 2021/4/4
 */
@Slf4j
class OptionalTest {


    @ParameterizedTest
    @MethodSource("personSource")
    void test1(Person person) {
        final String insuranceName = Optional.ofNullable(person).flatMap(Person::getCar).flatMap(Car::getInsurance).map(Insurance::getName).orElse(null);
        log.info("{}", insuranceName);
    }


    /**
     * 创建 4 个 Person 对象
     * 1.有车，有保险
     * 2.有车，无保险
     * 3.无车，无保险
     * 4.null
     */
    static Stream<Person> personSource() {
        return Stream.of(new Person("玉皇大帝", new Car("御驾", new Insurance("天宫保险")))
                , new Person("孙悟空", new Car("宝马", null))
                , new Person("张三", null)
                , null);
    }
}
