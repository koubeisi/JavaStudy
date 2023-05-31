package com.koubs.thread.keywords.sync;

/**
 * 原子性
 * @author KouBeisi
 */
public class SyncAtomic implements Runnable{

    private volatile static Integer count;

    public SyncAtomic() {
       count = 0;
    }

    @Override
    public  void run() {
       synchronized(this) {
          for (int i = 0; i < 5; i++) {
             try {
                System.out.println(Thread.currentThread().getName() + ":" + (count++));
                Thread.sleep(100);
             } catch (InterruptedException e) {
                e.printStackTrace();
             }
          }
       }
    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(new SyncAtomic(),"SyncThread1");
        Thread thread2 = new Thread(new SyncAtomic(),"SyncThread2");
        thread1.start();
        thread2.start();

    }

}
