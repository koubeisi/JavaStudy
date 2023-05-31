package org.example.reflect.statics;

import org.example.reflect.statics.entity.CaptainAmericaMovie;
import org.example.reflect.statics.proxy.MovieAdProxy;
import org.example.reflect.statics.proxy.MovieTimeProxy;
import org.junit.jupiter.api.Test;

/**
 * <p>静态代理示例</p>
 *
 * 参考：<a href="https://juejin.cn/post/6844903568986603534">10分钟看懂动态代理设计模式</a><br>
 *
 * <p>
 * 优点：<br>
 * 1.代理模式在客户端与目标对象之间起到一个中介作用和保护目标对象的作用；<br>
 * 2.代理对象可以扩展目标对象的功能；<br>
 * 3.代理模式能将客户端与目标对象分离，在一定程度上降低了系统的耦合度。<br>
 * </p>
 * <p>
 * 缺点：<br>
 * 1.代理类需要与被代理类实现同样的接口，如果代理类有多个接口需要现同样功能，则需要在每个代理接口添加响应的代码；<br>
 *   例如：如果 Movie 还有一个暂停接口 pause ，需要计算暂停的时间，则需要在代理类中实现 pause 接口。<br>
 * 2.如果有另外一个对象也需要实现同样功能，则需要另外实现一个代理类，导致类太多。<br>
 *   例如：如果有一个 Bird 类有一个 fly 方法需要实现与 Movie 类同样计算时间的功能，则需要再写一个 Bird 代理类。<br>
 * </p>
 * <p>
 * 疑问：<br>
 * 1.静态代理是不是聚合？<br>
 *   是<br>
 * 2.聚合与组合的区别？<br>
 *   组合是一种较为紧密的关系，从生命周期上看，部分和整体是共存亡的关系。<br>
 *   聚合则是一种较为松散的关系，部分和整体的生命周期未必一致。<br>
 * 3.代理模式与装饰器模式区别？<br>
 * </p>
 * @author KouBeisi
 * @date 2021/5/11
 */
public class StaticsProxyTest {

    /**
     * 在电影开始前后插入广告
     */
    @Test
    void test01() {
        CaptainAmericaMovie captainAmericaMovie = new CaptainAmericaMovie();
        MovieAdProxy movieProxy = new MovieAdProxy(captainAmericaMovie);
        movieProxy.play();
    }

    /**
     * 计算电影播放的时间
     */
    @Test
    void test02(){
        CaptainAmericaMovie captainAmericaMovie = new CaptainAmericaMovie();
        MovieTimeProxy movieProxy = new MovieTimeProxy(captainAmericaMovie);
        movieProxy.play();
    }

    /**
     * 电影开始前后计算时间，然后插入广告，即计算的时间包括广告
     */
    @Test
    void test03(){
        CaptainAmericaMovie captainAmericaMovie = new CaptainAmericaMovie();
        MovieAdProxy movieLogProxy = new MovieAdProxy(captainAmericaMovie);
        MovieTimeProxy movieTimeProxy = new MovieTimeProxy(movieLogProxy);
        movieTimeProxy.play();
    }

    /**
     * 电影开始前后插入广告，然后计算时间，即计算的时间不包含广告
     */
    @Test
    void test04(){
        CaptainAmericaMovie captainAmericaMovie = new CaptainAmericaMovie();
        MovieTimeProxy movieTimeProxy = new MovieTimeProxy(captainAmericaMovie);
        MovieAdProxy movieLogProxy = new MovieAdProxy(movieTimeProxy);
        movieLogProxy.play();
    }
}
