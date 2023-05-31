package com.koubs.jvm.garbage;

import java.lang.ref.PhantomReference;

/**
 * @author KouBeisi
 * @since 2021/10/22
 */
public class PhantomReferenceTest {

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
//        PhantomReference<User> userPhantomReference = new PhantomReference<>(new User("Jerry",34));


    }
}
