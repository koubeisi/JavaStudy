package com.koubs.thread.thread._1_create;

import lombok.extern.slf4j.Slf4j;

/**
 * 主线程结束后子线程依然在运行，我们平时之所以让主线程sleep等子线程结束是为了获取子线程运行后的结果
 * @author KouBeisi
 */
@Slf4j
public class ThreadTest extends Thread {
    private final int num;

    public ThreadTest(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        int i = num;
        var result = 1;
        log.debug("New thread starts.");
        while (i > 0) {
            result = result * i;
            i--;
        }
        log.debug("The factorial of " + num + " = " + result);
        log.debug("New thread ends.");
    }

    public static void main(String[] args) {
        log.debug("Main thread starts.");
        var thread = new ThreadTest(10);
        thread.start();
//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            Thread.currentThread().interrupt();
//        }
        log.debug("Main thread ends.");
    }
}
