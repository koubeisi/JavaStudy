package org.example.loader;

import org.junit.jupiter.api.Test;

import java.net.URL;

/**
 * @author KouBeisi
 * @since 2021/9/26
 */
public class ClassLoaderTest {


    /**
     * 获取 ClassLoader
     */
    @Test
    void test(){
        final ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(appClassLoader);

        final ClassLoader extClassLoader = appClassLoader.getParent();
        System.out.println(extClassLoader);

        // 获取不到引导类加载器
        final ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println(bootstrapClassLoader);
    }


    /**
     * 获取 BootstrapClassLoader 能够加载 API 的路径
     */
    @Test
    void bootstrap() {
        final String version = System.getProperty("java.specification.version");
        // 1.8 版本以后无法获取 Launcher 类
//        if ("1.8".equals(version)) {
//            final URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
//            for (URL url : urls) {
//                System.out.println(url.toExternalForm());
//            }
//        }
    }

    /**
     * 获取 ExtClassLoader 能够加载 API 的路径
     */
    @Test
    void extern(){
        final String extDirs = System.getProperty("java.ext.dirs");
        for (String dir : extDirs.split(";")) {
            System.out.println(dir);
        }
    }
}
