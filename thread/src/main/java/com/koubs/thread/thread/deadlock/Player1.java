package com.koubs.thread.thread.deadlock;

public class Player1 extends Thread {
	private Ball ball;

	public Player1(Ball ball) {
		this.ball = ball;
	}

	@Override
	public void run() {
		while(true) {
			while(ball.isB1()==true) {}	//如果1号球被拿走了，则等待
			ball.setB1(true);	//拿到1号球
			System.out.println("Player1 got ball1.");
			while(ball.isB2()==true) {}	//如果2号球被拿走了，则等待
			if(ball.isB1()==true && ball.isB2()==true) {	//如果两个球都拿到了，把球放下
				ball.setB2(true);
				System.out.println("Player1 got two balls.");
				ball.setB1(false);
				ball.setB2(false);
//				try {
//					sleep(2000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
	}

}
