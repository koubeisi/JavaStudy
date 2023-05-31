package com.koubs.thread.thread.example.example1;

public class Produce extends Thread {

	private Tickets tickets = null;

	public Produce(Tickets tickets) {
		this.tickets = tickets;
	}

	@Override
	public void run() {

		while (tickets.getNum() < tickets.getSize()) {

			synchronized(tickets) {	//申请对象tickets的锁
				int num = tickets.getNum();
				tickets.setNum(++num);

				System.out.println("Producer puts tickets " + num);

				tickets.setAvaiable(true);
			}	//释放对象tickets的锁
		}

		System.out.println("所有的电影票都已经生产完！");
	}
}
