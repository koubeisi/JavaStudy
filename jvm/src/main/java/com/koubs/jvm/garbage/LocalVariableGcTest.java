package com.koubs.jvm.garbage;

/**
 * -Xms60M -Xmx60M -XX:+PrintGCDetails
 * @author KouBeisi
 * @since 2021/10/21
 */
public class LocalVariableGcTest {

    public void localGc1() {
        byte[] buffer = new byte[10 * 1024 * 1024];
        System.gc();
    }

    public void localGc2() {
        byte[] buffer = new byte[10 * 1024 * 1024];
        buffer = null;
        System.gc();
    }

    /**
     * 执行GC，虽然 buffer 声明在局部作用域中，但是局部变量表中还占着一个 slot 槽
     * 所以未能回收
     */
    public void localGc3() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        System.gc();
    }

    /**
     * 执行GC，作用域外声明的变量占用了 buffer 的 slot 槽，释放了引用
     * 所以回收了空间
     */
    public void localGc4() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        int i = 0;
        System.gc();
    }

    public void localGc5(){
        localGc1();
        System.gc();
    }

    public static void main(String[] args){
        LocalVariableGcTest gcTest = new LocalVariableGcTest();
        gcTest.localGc4();
    }
}
