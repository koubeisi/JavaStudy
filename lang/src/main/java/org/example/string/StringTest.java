package org.example.string;

import org.junit.jupiter.api.Test;

/**
 * @author koubeisi
 * @version v1.0
 * @description 这个类用来测试String在JVM是如何存储的，它在堆和常量池的区别。
 * @time 2018年11月17日上午9:09:19
 */
public class StringTest {

    /**
     * s1会在常量池中创建字符串，s2在常量池中查找到该字符串并直接引用 。so，the result is "true".
     */
    @Test
    void test() {
        String s1 = "abc";
        String s2 = "abc";
        System.out.println(s1 == s2);
    }

    /**
     * 两个引用指向堆中的不同对象.so the result is false.
     */
    @Test
    void test2() {
        String s1 = new String("abc");
        String s2 = new String("abc");
        System.out.println(s1 == s2);
    }

    /**
     * 当 s2 和 s3 没有被声明为 final 时，它们的值在编译时是不确定的。
     * 因此，编译器无法在编译时计算出表达式 s2 + s3 的值。
     * 在这种情况下，表达式 s2 + s3 的值将在运行时计算，并创建一个新的字符串对象来存储结果。
     * 由于 s4 引用了一个新创建的字符串对象，而不是字符串常量池中的对象，因此 s1 == s4 的结果为 false
     */
    @Test
    void test3() {
        String s1 = "abc";
        String s2 = "a";
        String s3 = "bc";
        String s4 = s2 + s3;
        System.out.println(s1 == s4);
    }


    @Test
    void test4() {
        String s1 = "abc";
        String s2 = "a" + "bc";
        System.out.println(s1 == s2);
    }

    /**
     * 程序的执行结果为 true，是因为 s2 和 s3 都被声明为 final，因此它们的值在编译时就已经确定。
     * 在编译时，编译器会将表达式 s2 + s3 的值计算出来，并将其替换为常量 “abc”。
     * 因此，s4 实际上被初始化为 String s4 = "abc"。
     */
    @Test
    void test5() {
        String s1 = "abc";
        final String s2 = "a";
        final String s3 = "bc";
        String s4 = s2 + s3;
        System.out.println(s1 == s4);
    }

    @Test
    void test6() {
        String s = new String("abc");
        String s1 = "abc";
        String s2 = new String("abc");
        System.out.println(s == s1.intern());
        System.out.println(s == s2.intern());
        System.out.println(s1 == s2.intern());
        System.out.println(s.intern() == s2.intern());

    }

    /**
     * 改例子中如果把 s1.intern() 和 String s2 = "ab"调换位置，又是另一种结果
     */
    @Test
    void test7() {
        // 等同于 String s1 = new StringBuilder().append("a").append("b").toString()，此时字符串常量池中并没有 ab 字符串
        String s1 = new String("a") + new String("b");
        // 将在字符串常量池中创建字符串 ab，由于堆堆中已存在字符串 ab，因此字符串常量池中的 ab 指向了堆中的 ab
        s1.intern();
        // 字符串常量池中的ab实际为堆中的 ab，也就是 s1 的地址
        String s2 = "ab";

        System.out.println(s1 == s2);
    }

    @Test
    void test8() {
        char data[] = {'a', 'b', 'c'};
        String s1 = new String(data);
        String s2 = "abc";
        String s3 = new String("abc");
        System.out.println(s1 == s2);
        System.out.println(s2 == s3);
    }
}
