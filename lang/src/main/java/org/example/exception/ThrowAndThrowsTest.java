package org.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * 该类用来测试 RuntimeException 和 non-RuntimeException 用 throw 和 throws 的区别 <br>
 * 结论：
 * 1.RuntimeException 一般属于代码逻辑错误，不需要捕获，编辑器也不会提示捕获
 * 2.Non-RuntimeException 必须捕获或向上抛出，编辑器会提示必须捕获
 *
 * @author koubeisi
 * @version v1.0
 * @date 2019年4月5日下午5:37:49
 */
@Slf4j
class ThrowAndThrowsTest {

    @Test
    void test01() {
        test1();
    }

    @Test
    void test02() {
        try {
            test2();
        } catch (NullPointerException e){
            log.info(e.getMessage());
        }
    }

    @Test
    void test03() {
        try {
            test3();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test04() {
        try {
            test4();
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }

    /**
     * NullPointerException 继承至 RuntimeException 不需要捕获
     */
    private static void test1() {

        throw new NullPointerException("throw exception1");

    }

    /**
     * 即使再次抛出，也不需要捕获
     */
    private static void test2() {
        try {
            throw new NullPointerException("throw exception2");
        } catch (NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * Non-RuntimeException 必须捕获
     * @throws IOException IO异常
     */
    private static void test3() throws IOException {
        throw new IOException("IO 异常");
    }

    private static void test4() throws IOException {
        try {
            throw new IOException("IO 异常");
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * 继承 Exception 的自定义异常必须抛出异常
     */
    private void test5() throws CustomException {
        throw new CustomException("自定义异常");
    }

    /**
     * 继承 RuntimeException 的自定义异常无需抛出异常
     */
    private void test6(){
        throw new CustomRuntimeException("自定义运行时异常");
    }

}

class CustomException extends Exception{

    public CustomException(){
        super();
    }

    public  CustomException(String message){
        super(message);
    }
}

class CustomRuntimeException extends RuntimeException{
    public CustomRuntimeException() {
        super();
    }

    public CustomRuntimeException(String message) {
        super(message);
    }
}
