package com.koubs.thread.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author KouBeisi
 */
@Slf4j
public class ThreadLocalTest {

    /**
     * 当线程没有结束，但是ThreadLocal已经被回收，则可能导致线程中存在ThreadLocalMap<null, Object>的键值对，造成内存泄露。
     * ThreadLocal被回收，ThreadLocal关联的线程共享变量还存在。
     * 虽然ThreadLocal的get，set方法可以清除ThreadLocalMap中key为null的value，但是get，set方法在内存泄露后并不会必然调用，所以为了防止此类情况的出现，我们有两种手段。
     * 1、使用完线程共享变量后，显示调用ThreadLocalMap.remove方法清除线程共享变量；
     * 2、JDK建议ThreadLocal定义为private static，这样ThreadLocal的弱引用问题则不存在了。 ֵ
     */
    //关于ThreadLocalMap<ThreadLocal, Object>弱引用问题：
    /*
     * 由于其key是弱引用，当key对应的线程结束时，该对应的key对象就会被回收，保证内存不泄露
     */

    public static final ThreadLocal<Integer> SEQ_NUM = ThreadLocal.withInitial(() -> 12);

    public static int getNextNum() {
        SEQ_NUM.set(SEQ_NUM.get() + 1);
        return SEQ_NUM.get();
    }

    @Test
    void test1(){
        System.out.println(SEQ_NUM.get());
        SEQ_NUM.remove();
        System.out.println(SEQ_NUM.get());
        SEQ_NUM.set(13);
        System.out.println(SEQ_NUM.get());

    }

    @Test
    void test() throws InterruptedException {

        //由于使用了ThreadLocal，即使同一对象传入了不同的线程，但各种线程操作的都是自己线程的本地变量副本
        Runnable sn = new TestClient();
        Thread t1 = new Thread(sn);
        Thread t2 = new Thread(sn);
        Thread t3 = new Thread(sn);

        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();
    }

    private static class TestClient implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                    log.info("thread[{}] sn[{}]", Thread.currentThread().getName(), getNextNum());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                } finally {
                    SEQ_NUM.remove();
                }
            }
        }
    }
}
