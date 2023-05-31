package com.koubs.socket._4_four;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author KouBeisi
 * @since 2021/8/23
 */
public class ServerSocketPool {

    /**
     * 1.创建线程池的成员变量用以存储一个线程池对象
     */
    private final ExecutorService executorService;

    /**
     * 2.初始化线程池的参数
     */
    public ServerSocketPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit){
        executorService = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit, new ArrayBlockingQueue<>(maximumPoolSize));
    }

    /**
     * 3.提供一个方法来提交任务给线程池的任务队列处理
     */
    public void execute(Runnable target){
        executorService.execute(target);
    }
}
