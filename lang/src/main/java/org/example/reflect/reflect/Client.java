package org.example.reflect.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 这是Java动态代理实例，来源学堂在线《Java程序设计进阶》
 * @author Max
 *
 */
public class Client {
	
	public static void main(String[] args) {
		//指定被代理类
		RealSubject rs = new RealSubject();
		
		InvocationHandler ds = new DynamicSubject(rs);
		
		Class<?> cls = rs.getClass();
		//一次性生成代理
		Subject subject = (Subject) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), ds);
		
		subject.request();
	}

}
