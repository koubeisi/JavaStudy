package com.koubs.thread.thread.example.example1;

public class Consume extends Thread {

	Tickets tickets = null;
	//卖出的票的编号
	int i = 0;

	public Consume(Tickets tickets) {
		this.tickets = tickets;
	}

	public void run() {

		while (i<tickets.getSize()) {

			synchronized(tickets) {	//申请对象tickets的锁

				if(tickets.isAvaiable() && i < tickets.getNum()) {
					i++;
					System.out.println("Consumer buys ticket " +i);
				}
				//现有的票卖完了
				if(i==tickets.getNum()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					tickets.setAvaiable(false);
				}
			}	//释放对象tickets的锁
		}
		System.out.println("所有的电影票都已经卖完！");
	}

}
