package org.example.reflect.dynamic.handler;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author KouBeisi
 * @date 2021/5/11
 */
@Slf4j
public class AdInvocationHandler implements InvocationHandler {

    private final Object object;

    public AdInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        playStart();
        Object invoke = method.invoke(object, args);
        playEnd();
        return invoke;
    }

    private void playStart(){
        log.info("电影开始，播放广告");
        try {// 模拟广告
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.info(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private void playEnd(){
        log.info("电影结束，播放广告");
        try {// 模拟广告
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.info(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
