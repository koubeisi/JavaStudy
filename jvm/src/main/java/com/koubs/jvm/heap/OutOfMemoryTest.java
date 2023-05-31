package com.koubs.jvm.heap;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author KouBeisi
 * @since 2021/10/19
 */
public class OutOfMemoryTest {


    public static void main(String[] args) {
        ArrayList<Picture> list = new ArrayList<Picture>();

        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new Picture(new Random().nextInt(1024 * 1024)));
        }
    }
}

class Picture {
    byte[] bytes;

    Picture(int size) {
        bytes = new byte[size];
    }
}
