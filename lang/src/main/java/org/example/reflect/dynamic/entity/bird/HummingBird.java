package org.example.reflect.dynamic.entity.bird;

import lombok.extern.slf4j.Slf4j;

/**
 * @author KouBeisi
 * @date 2021/5/11
 */
@Slf4j
public class HummingBird implements Bird{
    @Override
    public void fly() {
        log.info("蜂鸟在飞...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.info(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
