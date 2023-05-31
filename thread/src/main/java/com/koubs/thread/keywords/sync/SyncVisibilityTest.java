package com.koubs.thread.keywords.sync;

/**
 * 可见性
 * @author KouBeisi
 * @since 2021/11/10
 */
public class SyncVisibilityTest {

    private static boolean flag=true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while(flag){
                // 如果不加该同步块则不能保证可见性
                // synchronized 在获取锁时，会刷新工作内存共享变量的值，从而获取flag最新值
                synchronized (SyncVisibilityTest.class){}
            }
            System.out.println(Thread.currentThread().getName() + " ends");
        },"thread-1").start();

        Thread.sleep(2000);

        new Thread(()->{
            flag=false;
            System.out.println("flag has been changed");
        },"thread-2").start();



    }
}
