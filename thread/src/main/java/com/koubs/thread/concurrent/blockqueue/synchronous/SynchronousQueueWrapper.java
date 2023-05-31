package com.koubs.thread.concurrent.blockqueue.synchronous;

import lombok.Data;

import java.util.concurrent.SynchronousQueue;

/**
 * @author KouBeisi
 * @since 2021/8/23
 */
@Data
public class SynchronousQueueWrapper {

    private final SynchronousQueue<Integer> synchronousQueue;

    public SynchronousQueueWrapper(SynchronousQueue<Integer> synchronousQueue){
        this.synchronousQueue = synchronousQueue;
    }

}
