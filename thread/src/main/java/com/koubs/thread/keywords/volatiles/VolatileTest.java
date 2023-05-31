package com.koubs.thread.keywords.volatiles;

/**
 * 测试volatile的可见性
 * 去掉 volatile 观察程序的运行结果，发现线程 a1 一直在运行，并会不结束，因为主线程更改的共享变量 quit 对子线程不可见
 *
 * @author koubeisi
 * @version 2019年4月6日下午12:33:06
 */
public class VolatileTest implements Runnable {

    /**
     * 加上volatile关键字后。当一个线程对变量进行修改会更新自己的工作内存里面的值，然后立即将改动的值刷新到主内存，
     * 同时线程2的工作内存的quit副本缓存失效，下次直接到主内存读取
     */
    volatile boolean quit = false;
//    boolean quit = false;
    int i = 0;

    @Override
    public void run() {
        while (!quit) {
            i++;
        }
        System.out.println("线程退出");
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileTest vt = new VolatileTest();
        Thread a1 = new Thread(vt, "a1");
        Thread a2 = new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("开始通知线程结束");
                vt.setQuit(true);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        });
        a2.start();
        a1.start();
        Thread.sleep(1000);
    }

    public boolean isQuit() {
        return quit;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }
}
