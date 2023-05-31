package com.koubs.reflect.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxySubject implements InvocationHandler {
	private Object obj;

	public ProxySubject(Object obj) {
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();

		method.invoke(obj, args);

		after();
		return null;
	}

	private void before() {
		System.out.println("我是代理————我负责找房，看房。" + getClass());
	}
	private void after() {
		System.out.println("我是代理————我负责银行贷款事宜！" + getClass());
	}
}
