package time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author Max
 * @version 1.0
 * @date 2020/4/19 23:13
 **/
public class Convert {

    /**
     * Instant对象转换为ZoneDateTime对象
     * @param instant
     * @param zoneId
     * @return
     */
    public static ZonedDateTime convertFromInstant(Instant instant, ZoneId zoneId){
        return instant.atZone(zoneId);
    }

    /**
     * ZoneDateTime对象转换为LocalDateTime对象
     * @param zonedDateTime
     * @return
     */
    public static LocalDateTime convertFromZonedDateTime(ZonedDateTime zonedDateTime){
        return zonedDateTime.toLocalDateTime();
    }

    /**
     * Date对象转换为Instant对象
     * @param date
     * @return
     */
    public static Instant convertFromDate(Date date){
        return date.toInstant();
    }

}
