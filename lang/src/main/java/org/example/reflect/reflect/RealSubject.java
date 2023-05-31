package org.example.reflect.reflect;

public class RealSubject implements Subject {
	
	public RealSubject() {}

	@Override
	public void request() {
		System.out.println("From real subject");
	}

}
