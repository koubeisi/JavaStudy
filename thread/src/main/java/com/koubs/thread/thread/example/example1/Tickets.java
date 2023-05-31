package com.koubs.thread.thread.example.example1;

public class Tickets {
	// 票号
	private int num=0;
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

	public boolean isAvaiable() {
		return avaiable;
	}

	public void setAvaiable(boolean avaiable) {
		this.avaiable = avaiable;
	}
}
