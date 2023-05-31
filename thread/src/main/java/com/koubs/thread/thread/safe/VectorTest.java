package com.koubs.thread.thread.safe;

import java.util.Vector;

/**
 * @author KouBeisi
 */
public class VectorTest {

	public static Vector<Integer> vector=new Vector<>();

	public static void main(String[] args) {

		while(true) {
			for(int i=0;i<10;i++) {
				vector.addElement(i);
			}

			Thread thread1 =new Thread(new Runnable() {

				@Override
				public void run() {
//					synchronized(vector)
					{

						for(int i=0;i<vector.size();i++) {
							vector.removeElementAt(i);
						}
					}
				}

			});

			Thread thread2=new Thread(new Runnable() {

				@Override
				public void run() {
//					synchronized(vector)
					{

						for(int i=0;i<vector.size();i++) {
							System.out.println(vector.get(i));
						}
					}
				}

			});

			thread1.start();
			thread2.start();
			System.out.println("ActiveCount:" + Thread.activeCount());
			while(Thread.activeCount() > 10) {
				;
			}
		}

	}
}
