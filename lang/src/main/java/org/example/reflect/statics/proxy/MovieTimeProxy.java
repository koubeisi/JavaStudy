package org.example.reflect.statics.proxy;

import lombok.extern.slf4j.Slf4j;
import org.example.reflect.statics.entity.Movie;

/**
 * 计算电影播放时间
 * @author KouBeisi
 * @date 2021/5/11
 */
@Slf4j
public class MovieTimeProxy implements Movie {

    private final Movie movie;

    public MovieTimeProxy(Movie movie) {
        this.movie = movie;
    }

    @Override
    public void play() {
        long start = System.currentTimeMillis();
        movie.play();
        long end = System.currentTimeMillis();
        log.info("电影时长：{}",end - start);

    }
}
