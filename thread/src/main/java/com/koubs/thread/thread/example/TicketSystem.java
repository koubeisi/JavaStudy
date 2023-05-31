package com.koubs.thread.thread.example;

/**
 * @author KouBeisi
 */
public class TicketSystem implements Runnable {
	private int n;
	public TicketSystem(int n) {
		this.n = n;
	}
	@Override
	public void run() {
		while(n > 0) {
			System.out.println(Thread.currentThread().getName() + "剩余" + n + "张票！");
			n--;
		}
		System.out.println(Thread.currentThread().getName() + " 已销售完！");
	}

	public static void main(String[] args) {
		TicketSystem ts = new TicketSystem(80);
		System.out.println("Threads start...");
		new Thread(ts,"TCinema").start();
		new Thread(ts,"MCinema").start();
		new Thread(ts,"SCinema").start();
		System.out.println("Main ends.");
	}
}
