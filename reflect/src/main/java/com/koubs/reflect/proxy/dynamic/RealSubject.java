package com.koubs.reflect.proxy.dynamic;

/**
 * @author KouBeisi
 */
public class RealSubject implements Subject {

	@Override
	public void request() {
		System.out.println("我是委托————我付钱买房子。" + getClass());
	}

}
