package new_api;

import org.junit.jupiter.api.Test;

import java.time.*;

/**
 * @author Max
 * @version 1.0
 * @date 2020/4/19 11:46
 **/
class ClassMethodDemo {

    @Test
    void main() {

        Instant instant = Instant.now();
        System.out.println(instant);

        LocalTime time = LocalTime.now();
        System.out.println(time);

        LocalDate date = LocalDate.now();
        System.out.println(date);

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);

    }



}
