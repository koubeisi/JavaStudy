package com.koubs.reflect.proxy.dynamic;

/**
 * @author KouBeisi
 */
public interface Subject {
	@MyAnnotation("interface method")
	void request();

	void response();
}
