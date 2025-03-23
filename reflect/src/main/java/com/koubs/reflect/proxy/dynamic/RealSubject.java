package com.koubs.reflect.proxy.dynamic;

/**
 * @author KouBeisi
 */
public class RealSubject implements Subject {

	@Override
	@MyAnnotation("class method") // 这个注解不会被 Proxy 读取到
	public void request() {
		System.out.println("我是 Request" + getClass());
	}

	@Override
	public void response() {
		System.out.println("我是 Response" + getClass());
	}

}
