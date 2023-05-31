package com.koubs.thread.thread._1_create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description 该程序用来测试Future和Callable的使用
 * @author koubeisi
 * @time 2018年12月12日下午9:32:45
 * @version v1.0
 */
public class FutureDemo {

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Future<String> future1 = executor.submit(new CallableDemo());
        Future<String> future2 = executor.submit(new CallableDemo());

        //获取返回值会阻塞程序
        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println("test");
        executor.shutdown();

    }

}

class CallableDemo implements Callable<String>{

    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName());
        return "Callable";
    }
}

