package problem;

import java.util.Calendar;

/**
 * 不允许使用没有定义的魔法数字
 * 示例：初始化Calendar，封装日期为2008年9月8日
 * @author Max
 * @version 1.0
 * @date 2020/4/19 1:49
 **/
public class CalendarUnsafeDemo {

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();

        //通过set方法设置年月日参数
        //开发规范：不允许使用没有定义的魔法数字
        //month是从0开始的，因此9月是8
        calendar.set(2009,8,8);

        //使用枚举
        calendar.set(2009,Calendar.SEPTEMBER,8);

        System.out.println(calendar.toInstant());

    }
}
