package com.koubs.thread.thread.deadlock;

public class Player3 extends Thread {
	private Ball ball;

	public Player3(Ball ball) {
		this.ball = ball;
	}

	@Override
	public void run() {
		while(true) {
			while(ball.isB3()==true) {}
			ball.setB3(true);
			System.out.println("Player3 got ball3.");
			while(ball.isB1()==true) {}
			if(ball.isB3()==true && ball.isB1()==true) {
				ball.setB1(true);
				System.out.println("Player1 got two balls.");
				ball.setB3(false);
				ball.setB1(false);
//				try {
//					sleep(2000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
	}

}
