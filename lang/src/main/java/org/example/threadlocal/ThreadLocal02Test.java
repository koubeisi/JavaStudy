package org.example.threadlocal;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * <a href="https://mp.weixin.qq.com/s/NrHIOzrz_Okwts4CIXZOkQ">一个ThreadLocal和面试官大战30个回合</a>
 * @author KouBeisi
 * @date 2021/5/14
 */
@Slf4j
class ThreadLocal02Test {

    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Test
    void test() throws InterruptedException {
        ThreadFactory threadFactory = ThreadFactoryBuilder.create().setNamePrefix("thread-local-pool-").build();
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), threadFactory);
        final int count = 5;
        for (int i = 0; i < count; i++) {
            executorService.execute(() -> {
                threadLocal.set(Thread.currentThread().getName() + "的文章");
                log.info("{}:{}",Thread.currentThread().getName(),threadLocal.get());
                threadLocal.remove();
            });
        }
        executorService.shutdown();
        boolean result = executorService.awaitTermination(20, TimeUnit.SECONDS);
        if (result) {
            log.info("线程结束");
        }

    }
}
