package com.koubs.thread.concurrent.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author KouBeisi
 */
public class FixedThreadPool {

    public static void main(String[] strs) {
        //创建线程池，该线程池最多只能有3个线程
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        //创建20个线程，分别输出线程的名字
        for(int i=0;i<10;i++) {

            //由于主线程与线程池无关，该字符串立刻输出
            System.out.println("Main Thread");

            fixedThreadPool.execute(() -> {
                try {
                    //由于线程池最大线程数为3，因此每建立3个线程，后面的线程等待前面的线程结束后才能建立
                    //输出结果就会显示每个2秒输出3个线程名
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        // JDK19引入的关闭方法，实现了 AutoCloseable 接口，内部调用 shutdown 方法，为了支持 try-with-resources 语法
        fixedThreadPool.close();
    }

}
