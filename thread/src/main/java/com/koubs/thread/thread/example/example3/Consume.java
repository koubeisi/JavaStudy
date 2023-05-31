package com.koubs.thread.thread.example.example3;

public class Consume extends Thread {

	Tickets tickets = null;

	public Consume(Tickets tickets) {
		this.tickets = tickets;
	}

	public void run() {

		while (tickets.getId()<tickets.getSize()) {

			try {
				tickets.sell();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
