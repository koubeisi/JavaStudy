package com.koubs.thread.thread.priority;

public class Init {

	public static void main(String[] args) {
		CThread[] t = {new CThread("1") , new CThread("2")};
		t[0].setPriority(4);
		t[1].setPriority(1);
		t[0].start();
		t[1].start();

	}

}
