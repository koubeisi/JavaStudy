package com.koubs.jvm.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author KouBeisi
 * @since 2024/10/21
 */
public class BreakClassLoaderI extends ClassLoader{


    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        // 打破双亲委派机制，不先委派给父类加载器
        System.out.println("Trying to load class: " + name);

        // 过滤掉核心类库（例如 java.lang.*），这些类必须由引导类加载器加载
        if (name.startsWith("java.")) {
            return super.loadClass(name);
        }
        // 将类的名称转化为路径格式：com.example.MyClass -> com/example/MyClass.class
        String fileName = name.replace('.', '/') + ".class";
        try(InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                throw new ClassNotFoundException("Class not found: " + name);
            }

            // 读取类文件的字节码
            byte[] classBytes = new byte[is.available()];
            is.read(classBytes);

            // 定义类，使用字节码定义类对象
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Error loading class: " + name, e);
        }
    }

    public static void main(String[] args) {
        BreakClassLoaderI loader = new BreakClassLoaderI();
        try {
            Class<?> clazz = loader.loadClass("com.koubs.jvm.classloader.A");
            System.out.println(clazz.getClassLoader());
            clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
