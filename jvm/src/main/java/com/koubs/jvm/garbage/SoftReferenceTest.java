package com.koubs.jvm.garbage;

import java.lang.ref.SoftReference;

/**
 * -Xms7M -Xmx7M
 * @author KouBeisi
 * @since 2021/10/22
 */
public class SoftReferenceTest {

    public static class User {
        private final String name;
        private final int age;

        public User(String name, int age) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) {

        User user = new User("Jerry", 33);

        SoftReference<User> userSoftReference = new SoftReference<>(user);

        System.out.println(userSoftReference.get());

        user = null;
        System.gc();
        System.out.println(userSoftReference.get());

        // -Xms7M -Xmx7M
        try {
            // 创建一个大数字，让内存不足，触发 GC
            byte[] bytes = new byte[5 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(userSoftReference.get());
        }
    }

}
