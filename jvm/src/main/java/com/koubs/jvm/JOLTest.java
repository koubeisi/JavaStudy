package com.koubs.jvm;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @author PC
 * @since 2024/9/4
 */
public class JOLTest {


    @Test
    public void test1(){
        // 打印当前虚拟机的详细信息
        System.out.println(VM.current().details());
        // 打印当前虚拟机对象的对齐大小
        System.out.println(VM.current().objectAlignment());
    }


    @Test
    public void test2(){
        // java.lang.Object object internals:
        //OFF  SZ   TYPE DESCRIPTION               VALUE
        //  0   8        (object header: mark)     0x0000000000000001 (non-biasable; age: 0)
        //  8   4        (object header: class)    0x00000e80
        // 12   4        (object alignment gap)
        //Instance size: 16 bytes
        //Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
        var obj = new Object();
        System.out.println(Integer.toHexString(obj.hashCode()) );
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }


    @Test
    public void test3(){
        var obj = new Custom();
        // com.koubs.jvm.Custom object internals:
        //OFF  SZ   TYPE DESCRIPTION               VALUE
        //  0   8        (object header: mark)     0x0000000000000001 (non-biasable; age: 0)
        //  8   4        (object header: class)    0x010ad7d8
        // 12   4        (object alignment gap)
        //Instance size: 16 bytes
        //Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }

    @Test
    public void test4(){
        var person = new Person();
        System.out.println(ClassLayout.parseInstance(person).toPrintable());
    }
}

class Custom{
}

class Person{
    private int id;
    private String name;
    private boolean flag;
    private boolean gender;
}
