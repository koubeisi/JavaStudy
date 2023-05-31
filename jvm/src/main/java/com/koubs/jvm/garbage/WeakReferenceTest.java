package com.koubs.jvm.garbage;

import java.lang.ref.WeakReference;

/**
 * @author KouBeisi
 * @since 2021/10/22
 */
public class WeakReferenceTest {

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
        WeakReference<User> userWeakReference = new WeakReference<>(new User("Jerry", 33));

        System.out.println(userWeakReference.get());

        System.gc();

        System.out.println(userWeakReference.get());
    }
}
