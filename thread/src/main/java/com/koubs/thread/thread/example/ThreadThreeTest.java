package com.koubs.thread.thread.example;

import java.util.Random;

/**
 * @author KouBeisi
 */
public class ThreadThreeTest extends Thread {
	int time;
	public ThreadThreeTest(String name) {
		super(name);
		new Random().nextInt(6000);
		time = (int)(Math.random()*6000);
	}
	@Override
	public void run() {
		System.out.println(this.getName() + " will sleep " + time + " ms");
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.getName() + " ends");
	}

	public static void main(String[] args) {
		ThreadThreeTest thread1 = new ThreadThreeTest("thread1");
		ThreadThreeTest thread2 = new ThreadThreeTest("thread2");
		ThreadThreeTest thread3 = new ThreadThreeTest("thread3");
		System.out.println("Starting threads");
		thread1.start();
		thread2.start();
		thread3.start();
		System.out.println("Threads start,main ends");
	}

}
