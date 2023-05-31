package com.koubs.thread.thread.example.example3;

public class Produce extends Thread {

	private Tickets tickets = null;

	public Produce(Tickets tickets) {
		this.tickets = tickets;
	}

	@Override
	public void run() {

		while (tickets.getNum() < tickets.getSize()) {

			try {
				tickets.put();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}
}
