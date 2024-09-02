package com.koubs.thread.thread.priority;

/**
 *
 * @author KouBeisi
 */
public class CThread extends Thread {
	private int num;
	public CThread(String name) {
		super(name);
		num = 0;
	}
	@Override
	public void run() {
		while(num < 400000) {
			num++;
			if(num % 50000 == 0) {
				System.out.println("Thread" + this.getName() + ":" + num);
//				yield();
			}
		}
	}
}
