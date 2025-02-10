package com.koubs.reflect.proxy.dynamic;

/**
 * @author KouBeisi
 */
public class RealSubject implements Subject {

	@Override
	@MyAnnotation("class method") // 这个注解不会被 Proxy 读取到
	public void request() {
		System.out.println("我是委托————我付钱买房子。" + getClass());
	}

}
