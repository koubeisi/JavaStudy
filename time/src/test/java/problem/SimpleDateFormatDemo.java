package problem;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * SimpleDateFormat在多线程的情况下会出现问题
 * 示例：创建10个线程，将字符串 2018-12-12 12：12：12 转换为对象打印到控制台
 * @author Max
 * @version 1.0
 * @date 2020/4/19 0:07
 **/
public class SimpleDateFormatDemo {

    public static void main(String[] args) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            System.out.println(simpleDateFormat.parse("2018-12-12 12:12:12"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {

            new Thread(
                    () -> {
                        try {
//                            synchronized (simpleDateFormat){
                                System.out.println(simpleDateFormat.parse("2018-12-12 12:12:12"));
//                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
            ).start();
        }
    }
}
