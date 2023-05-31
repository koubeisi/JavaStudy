package org.example.reflect.statics.proxy;

import lombok.extern.slf4j.Slf4j;
import org.example.reflect.statics.entity.Movie;

/**
 * 在电影前后插入广告
 * @author KouBeisi
 * @date 2021/5/11
 */
@Slf4j
public class MovieAdProxy implements Movie {

    private final Movie movie;

    public MovieAdProxy(Movie movie) {
        this.movie = movie;
    }

    @Override
    public void play() {
        log.info("电影开始，播放广告");
        try {// 模拟广告
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.info(e.getMessage());
            Thread.currentThread().interrupt();
        }
        movie.play();
        log.info("电影结束，播放广告");
        try {// 模拟广告
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.info(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
