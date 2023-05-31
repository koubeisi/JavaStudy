package com.koubs.thread.concurrent.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
  *  线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，
  *  规避资源耗尽的风险。
  *  说明：Executors返回的线程池对象的弊端如下：
 * 1）FixedThreadPool和SingleThreadPool:允许的请求队列长度为Integer.MAX_VALUE，可能会堆积大量的请求，从而导致OOM。
 * 2）CachedThreadPool和ScheduledThreadPool:允许的创建线程数量为Integer.MAX_VALUE，可能会创建大量的线程，从而导致OOM。
 * @description
 * @author koubeisi
 * @time 2019年4月19日下午3:29:28
 * @version v1.0
 */
public class CachedThreadPool {

    public static void main(String[] args) {

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        //创建10个线程，分别输出它们的名字
        for (int i = 0; i < 20; i++) {

            //休眠为了使得每次创建新线程之前，等待前面的线程结束
            //这样根据输出的线程名字可以判断是否为线程池里的同一线程
            //
            //注释掉该代码块时，使得在每次创建新线程之前，无需考虑之前的线程是否结束
            //这样根据输出的线程名字判断是否利用线程池里原来的线程，是否在线程池创建了新线程
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });

        }

        cachedThreadPool.shutdown();
    }
}
