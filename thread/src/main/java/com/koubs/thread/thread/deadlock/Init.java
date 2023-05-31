
package com.koubs.thread.thread.deadlock;

public class Init {

	public static void main(String[] args) {
		Ball ball = new Ball();
		Player1 p1 = new Player1(ball);
		Player2 p2 = new Player2(ball);
		Player3 p3 = new Player3(ball);

		p1.start();
		p2.start();
		p3.start();
	}

}
