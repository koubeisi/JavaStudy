package org.example.reflect.dynamic.entity.movie;

import lombok.extern.slf4j.Slf4j;

/**
 * @author KouBeisi
 * @date 2021/5/11
 */
@Slf4j
public class IronManMovie implements Movie{
    @Override
    public void play() {
        log.info("影厅正在播放的电影是《钢铁侠》");
    }

    @Override
    public void pause() {
        log.info("影厅暂停播放电影《钢铁侠》");
    }
}
