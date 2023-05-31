package uitl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Calendar对象与Date对象的互相转换
 * Date对象与String对象的互相转换
 * @author Max
 * @version 1.0
 * @date 2020/4/19 2:08
 **/
public class Convert {

    public static void main(String[] args) throws ParseException {

        Date date =  new Date();
        System.out.println(convertFromDate(date,"yyyy-MM-dd hh:mm:ss"));

        String str = "2020-04-19 02:24:56";
        System.out.println(convertFromString(str));

    }

    /**
     * 从Date转换为Calendar
     * @param date
     * @return
     */
    public static  Calendar convertFromDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar;
    }

    /**
     * 从Calendar转换为Date
     * @param calendar
     * @return
     */
    public static Date convertFromCalendar(Calendar calendar){
        return calendar.getTime();
    }

    /**
     * 将字符串转换为Date
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date convertFromString(String str) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Date date = simpleDateFormat.parse(str);

        return date;
    }

    /**
     * 将Date转换为字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String convertFromDate(Date date, String pattern){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(date);
    }
}
