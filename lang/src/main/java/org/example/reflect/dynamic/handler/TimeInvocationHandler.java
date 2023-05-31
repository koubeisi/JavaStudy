package org.example.reflect.dynamic.handler;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author KouBeisi
 * @date 2021/5/11
 */
@Slf4j
public class TimeInvocationHandler implements InvocationHandler {

    private final Object object;

    public TimeInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        Object invoke = method.invoke(object, args);
        long end = System.currentTimeMillis();
        log.info("Time:{}",end - start);
        return invoke;
    }
}
