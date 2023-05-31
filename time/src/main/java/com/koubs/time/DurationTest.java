package com.koubs.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author KouBeisi
 * @since 2021/6/1
 */
@Slf4j
public class DurationTest {

    @Test
    void test() {
        LocalTime now1 = LocalTime.now();
        LocalTime now2 = LocalTime.now().plusMinutes(65).plusSeconds(30).plusHours(23);
        log.info(now2.toString());
        long between = ChronoUnit.SECONDS.between(now1,now2);
        LocalTime localTime = LocalTime.ofSecondOfDay(between);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        log.info(dateTimeFormatter.format(localTime));
    }
}
