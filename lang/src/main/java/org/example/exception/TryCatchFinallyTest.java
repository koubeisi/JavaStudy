package org.example.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * 该程序用来测试catch块中return后finally块是否还会执行，以及是否会影响返回结果
 * @author koubeisi
 * @date 2019年4月5日下午5:10:09
 * @version v1.0
 */
@Slf4j
public class TryCatchFinallyTest {

    public static void main(String[] args) {
        log.info("{}",test());
    }

    @SuppressWarnings("null")
    private static int test() {
        int i = 10;
        try {
            String str = null;
            //此处会发生异常
            str.equals("abc");

        }catch(NullPointerException e) {
            i=20;
            return i;
        }finally {
            i=30;
            //即使catch块return后，finally依然还会执行
            log.info("finally:"+i);
        }

        return i;
    }

}
