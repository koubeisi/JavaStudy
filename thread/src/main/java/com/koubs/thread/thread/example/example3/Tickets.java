package com.koubs.thread.thread.example.example3;

public class Tickets {
	// 生产的票号
	private int num=0;
	//卖出的票号
	private int id=0;
	// 总票数
	private int size;
	// 表示当前是否有票可销售
	private boolean avaiable = false;

	public Tickets(int size) {
		this.size = size;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public synchronized void put() throws InterruptedException {

		if(avaiable) {	//如果有存票，则等待
			wait();
		}

		num++;
		System.out.println("生产电影票： " + num);
		avaiable=true;
		notify();

		if(num==size) {
			System.out.println("所有的电影票都已经生产完！");
		}
	}

	public synchronized void sell() throws InterruptedException {

		if(!avaiable) {	//如果没有存票，则等待
			wait();
		}

		id++;
		System.out.println("消费电影票：" + id);
		avaiable=false;
		notify();

		if(id==size) {
			System.out.println("所有的电影票都已经卖完！");
		}
	}
}
