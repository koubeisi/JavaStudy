package com.koubs.reflect.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author KouBeisi
 */
public class DynamicProxy {

	public static void main(String[] args) {
		RealSubject reals = new RealSubject();
		Class<?> c = reals.getClass();

		InvocationHandler proxys = new ProxySubject(reals);

		Subject subject = (Subject) Proxy.newProxyInstance(c.getClassLoader(), c.getInterfaces(), proxys);
		subject.request();
	}

}
