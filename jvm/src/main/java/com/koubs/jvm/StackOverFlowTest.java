package com.koubs.jvm;

/**
 * 启动时添加参数：-Xss512k ,观察 i 的值
 * @author KouBeisi
 * @since 2021/10/9
 */
public class StackOverFlowTest {

    private static int i = 0;

    public static void main(String[] args) {
        System.out.println(i);
        i++;
        main(args);
    }

}
