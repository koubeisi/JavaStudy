package com.koubs.reflect.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxySubject implements InvocationHandler {
	private final Object target;

	public ProxySubject(Object obj) {
		this.target = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 获取接口方法上的注解
		if (method.isAnnotationPresent(MyAnnotation.class)) {
			if("interface method".equals(method.getAnnotation(MyAnnotation.class).value())) {
				System.out.println("Proxy reads annotation from INTERFACE: " + method.getAnnotation(MyAnnotation.class).value());
			} else {
				System.out.println("Proxy reads annotation from CLASS: " + method.getAnnotation(MyAnnotation.class).value());
			}
		}
		// 获取真实方法的注解
		Method realMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());
		if (realMethod.isAnnotationPresent(MyAnnotation.class)) {
			if("interface method".equals(realMethod.getAnnotation(MyAnnotation.class).value())) {
				System.out.println("Proxy reads annotation from INTERFACE: " + realMethod.getAnnotation(MyAnnotation.class).value());
			} else {
				System.out.println("Proxy reads annotation from CLASS: " + realMethod.getAnnotation(MyAnnotation.class).value());
			}
		}

		before();

		method.invoke(target, args);

		after();
		return null;
	}

	private void before() {
		System.out.println("我是代理，我执行实际操作之前的行为" + getClass());
	}
	private void after() {
		System.out.println("我是代理，我只想实际操作之后的行为" + getClass());
	}
}
