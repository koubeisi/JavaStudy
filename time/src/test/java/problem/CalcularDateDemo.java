package problem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * 小李出生于1995年12月16日，计算他在当前已经出生了多少天
 * @author Max
 * @version 1.0
 * @date 2020/4/18 23:35
 **/
public class CalcularDateDemo {

    public static void main(String[] args) {

        oldApi();
        newApi();

        assert (oldApi() == newApi()) : "结果有误";

    }

    private static long oldApi(){
        //使用老版本
        Date now = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.set(1995, 12,16);
        Date birthday = calendar.getTime();


        long s1 = now.getTime();
        long s2 = birthday.getTime();

        long intervalDay = (s1 - s2) / 1000 / 60 / 60 / 24;
        System.out.println(intervalDay);

        return intervalDay;
    }

    private static long newApi(){
        LocalDate now = LocalDate.now();
        LocalDate birthday = LocalDate.of(1995,12,16);

        long intervalDay = ChronoUnit.DAYS.between(birthday, now);
        System.out.println(intervalDay);

        return intervalDay;
    }
}
