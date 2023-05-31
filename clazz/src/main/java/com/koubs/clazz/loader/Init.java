package com.koubs.clazz.loader;

/**
 * @author KouBeisi
 */
public class Init {

	public static void main(String[] args) throws ClassNotFoundException {

		System.out.println("=======Class.forName加载类======");
		Class<?> c1 = Class.forName("com.koubs.clazz.loader.Loader");
		System.out.println("加载的类的名字" + c1.getName());

		System.out.println("\n");

		System.out.println("=======ClassLoader.loadClass加载类======");
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		Class<?> c2 = classLoader.loadClass("com.koubs.clazz.loader.Loader");
		System.out.println("加载的类的名字：" + c2.getName());
	}
}
