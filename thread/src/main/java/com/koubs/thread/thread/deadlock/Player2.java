package com.koubs.thread.thread.deadlock;

public class Player2 extends Thread {
	private Ball ball;

	public Player2(Ball ball) {
		this.ball = ball;
	}

	@Override
	public void run() {
		while(true) {
			while(ball.isB2()==true) {}
			ball.setB2(true);
			System.out.println("Player2 got ball2.");
			while(ball.isB3()==true) {}
			if(ball.isB2()==true && ball.isB3()==true) {
				ball.setB3(true);
				System.out.println("Player1 got two balls.");
				ball.setB2(false);
				ball.setB3(false);
//				try {
//					sleep(2000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
	}

}
