package com.koubs.thread.thread.example.example2;

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

	public synchronized void put() {

		if(num<size) {
			num++;
			System.out.println("生产电影票： " + num);
			avaiable=true;
		}

		if(num==size) {
			System.out.println("所有的电影票都已经生产完！");
		}
	}

	public synchronized void sell() {

		if(avaiable && id<num) {
			id++;
			System.out.println("消费电影票：" + id);
		}

		if(id==num) {
			avaiable=false;
		}

		if(id==size) {
			System.out.println("所有的电影票都已经卖完！");
		}
	}
}
