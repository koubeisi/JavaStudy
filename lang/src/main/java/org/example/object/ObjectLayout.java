package org.example.object;

import org.openjdk.jol.info.ClassLayout;

/**
 * 对象头演示
 *
 * java.lang.Object object internals:
 * OFF  SZ   TYPE DESCRIPTION               VALUE
 *   0   8        (object header: mark)     0x0000000000000001 (non-biasable; age: 0)
 *   8   4        (object header: class)    0xf80001e5
 *  12   4        (object alignment gap)
 * @author KouBeisi
 * @since 2021/11/16
 */
public class ObjectLayout {

    public static void main(String[] args) {
        final Object obj = new Object();

        System.out.println(Integer.toHexString(obj.hashCode()));
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

    }
}


