package com.koubs.reflect.proxy.statics;

public class TestProxy {

	public static void main(String[] args) {
		RealSubject realSubject = new RealSubject();
		Subject subject = new ProxySubject(realSubject);
		subject.request();
	}
}
//委托对象和代理对象的共同接口或抽象类
abstract class Subject{
	public abstract void request();
}
//委托角色
class RealSubject extends Subject{
	@Override
	public void request() {
		System.out.println("From Real Subject!");
	}
}
//代理对象
class ProxySubject extends Subject{
	//代理对象内部含有真实对象的引用
	private RealSubject realSubject;
	public ProxySubject(RealSubject realSubject) {
		this.realSubject = realSubject;
	}
	@Override
	public void request() {
		//在真实角色之前附件的操作
		preRequest();

		//真实角色完成的事情
		realSubject.request();

		//在真实角色操作之后所附件的操作
		postRequest();
	}
	private void preRequest() {
		System.out.println("Pre Request.");
	}
	private void postRequest() {
		System.out.println("Post Request");
	}
}
