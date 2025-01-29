package com.koubs.thread.threadpool;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * 测试 CompletionService 的类 </br>
 * CompletionService 是一个用于处理异步计算结果的服务，它提供了更灵活的方式来处理 Future 对象 </br>
 * 参考：<a href="https://zhuanlan.zhihu.com/p/181120513">既生 ExecutorService, 何生 CompletionService？</a>
 *
 * @author KouBeisi
 * @since 2025/1/26
 */
class CompletionServiceTest {

    /**
     * 测试 ExecutorService 的使用
     * 该测试方法创建了一个固定大小的线程池，并提交了多个任务
     * 它演示了如何使用 ExecutorService 来执行任务，并使用 Future 来获取任务结果
     *
     * @throws InterruptedException 如果线程被中断
     * @throws ExecutionException   如果任务执行出错
     */
    @Test
    void testExecutorService() throws InterruptedException, ExecutionException {
        var start = System.currentTimeMillis();
        try (var executorService = Executors.newFixedThreadPool(4)) {
            var futures = new ArrayList<Future<Integer>>();
            futures.add(executorService.submit(() -> {
                // 模拟业务逻辑耗时
                TimeUnit.SECONDS.sleep(4);
                return 4;
            }));
            futures.add(executorService.submit(() -> {
                TimeUnit.SECONDS.sleep(1);
                return 2;
            }));
            futures.add(executorService.submit(() -> {
                TimeUnit.SECONDS.sleep(1);
                return 1;
            }));
            futures.add(executorService.submit(() -> {
                TimeUnit.SECONDS.sleep(1);
                return 1;
            }));

            // 遍历 Future list，通过 get() 方法获取每个 future 结果
            for (var future : futures) {
                Integer result = future.get();
                System.out.println(result);
                // 模拟线程完成后其他业务逻辑
                TimeUnit.SECONDS.sleep(1);
            }
        }
        var end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }

    /**
     * 测试 CompletionService 的使用
     * 该测试方法展示了如何使用 CompletionService 来处理任务的完成
     * CompletionService 提供了一种更优雅的方式来处理完成的任务，它允许你通过 take() 方法来获取已完成的任务
     *
     * @throws InterruptedException 如果线程被中断
     * @throws ExecutionException   如果任务执行出错
     */
    @Test
    void testCompletionService() throws InterruptedException, ExecutionException {
        var start = System.currentTimeMillis();
        try (var executorService = Executors.newFixedThreadPool(4)) {
            var completionService = new ExecutorCompletionService<Integer>(executorService);
            completionService.submit(() -> {
                // 模拟业务逻辑耗时
                TimeUnit.SECONDS.sleep(4);
                return 4;
            });
            completionService.submit(() -> {
                TimeUnit.SECONDS.sleep(1);
                return 2;
            });
            completionService.submit(() -> {
                TimeUnit.SECONDS.sleep(1);
                return 1;
            });
            completionService.submit(() -> {
                TimeUnit.SECONDS.sleep(1);
                return 1;
            });

            // 遍历 Future list，通过 get() 方法获取每个 future 结果
            for (int i = 0; i < 4; i++) {
                Integer result = completionService.take().get();
                System.out.println(result);
                // 模拟线程完成后其他业务逻辑
                TimeUnit.SECONDS.sleep(1);
            }
        }
        var end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }

}
