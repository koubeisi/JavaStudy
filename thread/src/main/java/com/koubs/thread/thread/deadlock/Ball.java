package com.koubs.thread.thread.deadlock;

public class Ball {
	private boolean b1;
	private boolean b2;
	private boolean b3;

	public Ball() {
		b1 = false;
		b2 = false;
		b3 = false;
	}

	public boolean isB1() {
		return b1;
	}

	public boolean isB2() {
		return b2;
	}

	public boolean isB3() {
		return b3;
	}

	public void setB1(boolean b1) {
		this.b1 = b1;
	}

	public void setB2(boolean b2) {
		this.b2 = b2;
	}

	public void setB3(boolean b3) {
		this.b3 = b3;
	}
}
