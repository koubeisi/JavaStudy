package com.koubs.thread.thread.example.example1;

public class TicketMaster {

	public static void main(String[] args) {

		Tickets tickets=new Tickets(10);

		Produce produce =new Produce(tickets);
		Consume consume=new Consume(tickets);

		produce.start();
		consume.start();

	}

}
