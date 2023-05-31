package org.example.reflect.dynamic;

import lombok.extern.slf4j.Slf4j;
import org.example.reflect.dynamic.entity.bird.Bird;
import org.example.reflect.dynamic.entity.bird.HummingBird;
import org.example.reflect.dynamic.entity.movie.IronManMovie;
import org.example.reflect.dynamic.entity.movie.Movie;
import org.example.reflect.dynamic.handler.AdInvocationHandler;
import org.example.reflect.dynamic.handler.TimeInvocationHandler;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

/**
 * <a href="https://mp.weixin.qq.com/s/6CdsdPOl4bF2oZLiW3pt9Q">终于有人把 java代理 讲清楚了，万字详解！</a>
 * @author KouBeisi
 * @since  2021/5/11
 */
@Slf4j
public class DynamicProxyTest {

    @Test
    void test01() {
        IronManMovie ironManMovie = new IronManMovie();
        AdInvocationHandler movieInvocationHandler = new AdInvocationHandler(ironManMovie);
        Movie proxyMovie = (Movie)Proxy.newProxyInstance(IronManMovie.class.getClassLoader(), IronManMovie.class.getInterfaces(), movieInvocationHandler);
        proxyMovie.play();
        proxyMovie.pause();
        // 通过 Proxy.newProxyInstance 创建的代理对象是在 jvm 运行时动态生成的一个对象，以$开头，proxy 为中，最后一个数字表示对象的标号
        log.info(proxyMovie.getClass().getName());
    }

    @Test
    void test02(){
        HummingBird hummingBird = new HummingBird();
        TimeInvocationHandler timeInvocationHandler = new TimeInvocationHandler(hummingBird);
        Bird proxyBird = (Bird)Proxy.newProxyInstance(HummingBird.class.getClassLoader(), HummingBird.class.getInterfaces(), timeInvocationHandler);
        proxyBird.fly();
    }
}
