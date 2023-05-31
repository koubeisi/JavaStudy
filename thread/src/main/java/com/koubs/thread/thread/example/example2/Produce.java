package com.koubs.thread.thread.example.example2;

public class Produce extends Thread {

	private Tickets tickets = null;

	public Produce(Tickets tickets) {
		this.tickets = tickets;
	}

	@Override
	public void run() {

		while (tickets.getNum() < tickets.getSize()) {

			tickets.put();

		}

	}
}
