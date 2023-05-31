package com.koubs.thread.thread.example.example3;

public class TicketMaster {

	public static void main(String[] args) {

		Tickets tickets=new Tickets(20);

		Produce produce =new Produce(tickets);
		Consume consume=new Consume(tickets);

		consume.start();
		produce.start();

	}
}
