package org.example.reflect.statics.entity;

import lombok.extern.slf4j.Slf4j;

/**
 * 问题一：我想在电影播放后自动计算电影播放的时间
 * 问题二：我想在电影开始和结束时分别输出日志
 * @author KouBeisi
 * @date 2021/5/11
 */
@Slf4j
public class CaptainAmericaMovie implements Movie{
    @Override
    public void play() {
      log.info("电影院正在播放电影《美国队长》");
        try {// 模拟电影播放
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.info("Interrupted:{}",e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
