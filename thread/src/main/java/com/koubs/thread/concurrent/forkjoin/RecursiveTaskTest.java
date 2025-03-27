package com.koubs.thread.concurrent.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 使用 ForkJoinPool 并行计算长整型数组的和
 */
public class RecursiveTaskTest extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;

    // 不再切分的任务大小阈值
    private static final int THRESHOLD = 10_000; // 经验值，可调整

    /**
     * 构造函数，用于创建主任务
     * @param numbers 要计算的数组
     */
    public RecursiveTaskTest(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    /**
     * 内部构造函数，用于递归创建子任务
     * @param numbers 要计算的数组
     * @param start   计算的起始索引（包含）
     * @param end     计算的结束索引（不包含）
     */
    private RecursiveTaskTest(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start; // 当前任务负责的数组长度

        // 1. 检查任务是否足够小（小于等于阈值）
        if (length <= THRESHOLD) {
            // 达到阈值，直接计算结果
            return computeSequentially();
        }

        // 2. 任务太大，需要分解 (Fork)
        // 计算中间点
        int middle = start + length / 2;

        // 创建左边的子任务
        RecursiveTaskTest leftTask = new RecursiveTaskTest(numbers, start, middle);
        // 创建右边的子任务
        RecursiveTaskTest rightTask = new RecursiveTaskTest(numbers, middle, end);

        // 异步执行左子任务 (fork)
        leftTask.fork();

        // *优化点*: 当前线程直接计算右子任务，而不是 fork 后再 join，可以减少线程创建和切换开销
        long rightResult = rightTask.compute();

        // 等待左子任务完成并获取结果 (join)
        long leftResult = leftTask.join();

        // 3. 合并 (Join) 结果
        return leftResult + rightResult;
    }

    /**
     * 顺序计算当前任务负责的部分
     * @return 部分和
     */
    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        // System.out.println(Thread.currentThread().getName() + " computed sum from " + start + " to " + end + ": " + sum);
        return sum;
    }

    public static void main(String[] args) {
        // 创建一个大数组
        int size = 100_000_000; // 一亿个元素
        long[] numbers = LongStream.rangeClosed(1, size).toArray();

        // 获取 ForkJoinPool 实例 (推荐使用 commonPool 或自定义)
        // ForkJoinPool pool = ForkJoinPool.commonPool(); // 使用公共池
        ForkJoinPool pool = new ForkJoinPool(); // 创建自定义池

        // 创建主任务
        RecursiveTaskTest task = new RecursiveTaskTest(numbers);

        System.out.println("Calculating sum for " + size + " numbers...");
        long startTime = System.currentTimeMillis();

        // 提交任务并同步等待结果
        long result = pool.invoke(task);

        long endTime = System.currentTimeMillis();
        System.out.println("ForkJoinPool Result: " + result);
        System.out.println("Time taken: " + (endTime - startTime) + " ms");

        // (可选) 对比单线程计算
        System.out.println("\nCalculating sequentially...");
        startTime = System.currentTimeMillis();
        long sequentialSum = 0;
        for (long number : numbers) {
            sequentialSum += number;
        }
        endTime = System.currentTimeMillis();
        System.out.println("Sequential Result: " + sequentialSum);
        System.out.println("Time taken: " + (endTime - startTime) + " ms");

        // 关闭自定义的 ForkJoinPool (commonPool 不需要手动关闭)
        pool.shutdown();
    }
}
