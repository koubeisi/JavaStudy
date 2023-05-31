package com.koubs.thread.thread._1_create;

import lombok.extern.slf4j.Slf4j;

/**
 * Thread 和 Runnable 的基本用法
 * 在该示例中可以看到，main 线程结束了 thread-1 仍然运行中
 * @author Max
 */
@Slf4j
public class RunnableTest{

	private static final String TEXT = "太阳在这个平静的小村庄缓缓升起，又是开始了平常的一天。我们故事的主人公睡眼惺忪的起来\n" +
			"......";

	public static void main(String[] args) throws InterruptedException {


		log.debug("Thread start:" + Thread.currentThread().getName());

		Thread thread = new Thread(new PrintStoryRunnable(TEXT,200L),"thread-1");
		//当设置 thread-1 为守护线程时，即使 thread-1 线程还没有结束，只要 main 线程结束，整个进程就结束了，控制台就不会再显示 thread-1 的输出结果了
		thread.setDaemon(true);
		//可以随时改变线程（和是不是守护线程）的优先级，但是作用不能保证
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.start();

//		Thread.sleep(TimeUnit.SECONDS.toMillis(5));

		log.debug("Thread end:"+ Thread.currentThread().getName());
	}
}

@Slf4j
class PrintStoryRunnable implements Runnable{

	private final String text;
	private final Long interval;

	public PrintStoryRunnable(String text,long interval){
		this.text = text;
		this.interval = interval;
	}

	@Override
	public void run() {
		log.debug("Thread start:" + Thread.currentThread().getName());

		char[] chars = text.toCharArray();

		for (char c : chars){
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
			System.out.print(c);
		}
		log.debug("\nThread end:"+ Thread.currentThread().getName());
	}
}


