package org.example.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author KouBeisi
 * @date 2021/4/21
 */
@Slf4j
public class StreamTest {


    /**
     * 外部迭代和内部迭代
     */
    @Test
    void test() {
        final int count = 100_0000;
        List<Integer> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            list.add(i);
        }

        //=========传统方式进行外部迭代=========
        Instant begin = Instant.now();
        for (int i : list) {
            System.out.print(i);
        }
        Instant end = Instant.now();


        //=========java8内部迭代，用lambda处理=========
        Instant begin1 = Instant.now();
        list.stream().forEach(System.out::print);
        Instant end1 = Instant.now();

        //=========java8进行并行流处理后迭代（备注：并行流输出是没有顺序的 比如不再是1234顺序了）=========
        Instant begin2 = Instant.now();
        list.parallelStream().forEach(System.out::print);
        Instant end2 = Instant.now();

        log.info("--------------------------");

        log.info("传统方式进行外部迭代" + count + "次,耗时(ms)：" + Duration.between(begin, end).toMillis());
        log.info("内部迭代forEach" + count + "次,耗时(ms)：" + Duration.between(begin1, end1).toMillis());
        log.info("内部迭代parallelStream" + count + "次,耗时(ms)：" + Duration.between(begin2, end2).toMillis());
    }
}
