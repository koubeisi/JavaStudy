package com.koubs.thread.thread.example;

public class RunnableThreeTest implements Runnable {
	private int time;
	public RunnableThreeTest() {
		time = (int)(Math.random()*6000);
	}
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " will sleep " + time + " ms");
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " ends");
	}

	public static void main(String[] args) {
		RunnableThreeTest thread1 = new RunnableThreeTest();
		RunnableThreeTest thread2 = new RunnableThreeTest();
		RunnableThreeTest thread3 = new RunnableThreeTest();
		System.out.println("Starting threads");
		new Thread(thread1,"thread1").start();
		new Thread(thread2,"thread2").start();
		new Thread(thread3,"thread3").start();
		System.out.println("Threads start,main ends");
	}

//	public static void main(String[] args) {
//		RunnableThreeTest thread = new RunnableThreeTest();
//		System.out.println("Starting threads");
//		new Thread(thread,"thread1").start();
//		new Thread(thread,"thread2").start();
//		new Thread(thread,"thread3").start();
//		System.out.println("Threads start,main ends");
//	}

}
