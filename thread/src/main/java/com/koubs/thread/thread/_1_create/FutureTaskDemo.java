package com.koubs.thread.thread._1_create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author KouBeisi
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executor = Executors.newCachedThreadPool();

        CallableImpl callableImpl = new CallableImpl();

        FutureTask<String> furtureTask1 = new MyFutureTask(callableImpl, "1");
        FutureTask<String> furtureTask2 = new MyFutureTask(callableImpl, "2");
        FutureTask<String> furtureTask3 = new MyFutureTask(callableImpl, "3");

        executor.submit(furtureTask1);
        executor.submit(furtureTask2);
        executor.submit(furtureTask3);

        // 此处获取运行结果
        System.out.println("FutureTask:get1" + furtureTask1.get());
        System.out.println("FutureTask:get2" + furtureTask2.get());
        System.out.println("FutureTask:get3" + furtureTask3.get());

        // 不会再运行
        executor.submit(furtureTask1);
        executor.submit(furtureTask1);

        executor.shutdown();

        System.out.println("Done");

    }

}

/**
 * 使用FutureTask能够处理一些线程完成后的数据
 */
class MyFutureTask extends FutureTask<String> {

    private String name;

    public MyFutureTask(Callable<String> callable, String name) {
        super(callable);
        this.name = name;
    }

    @Override
    protected void done() {
        System.out.println(Thread.currentThread().getName() + "FutureTask" + name);
    }

}

class CallableImpl implements Callable<String> {

    private int i;

    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName());
        System.out.println(i++);
        return "Callable" + i;
    }

}
