package com.koubs.jvm.reference;

import java.util.List;

/**
 * 弱引用示例类
 * 本类用于演示弱引用的使用方式及其在垃圾回收过程中的行为
 *
 * @author KouBeisi
 * @since 2024/10/22
 */
public class WeakReference {

    // 定义1MB的大小，用于后续创建字节数组
    private static final int _1MB = 1 * 1024 * 1024;

    /**
     * 主函数入口
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {

        // 创建一个列表，用于存储弱引用对象
        List<java.lang.ref.WeakReference<byte[]>> list = new java.util.LinkedList<>();
        // 创建并添加弱引用对象到列表中
        for (int i = 0; i < 10; i++) {
            // 创建一个弱引用，指向一个512KB的字节数组
            var weakReference = new java.lang.ref.WeakReference<>(new byte[_1MB / 2]);
            list.add(weakReference);
            System.out.println(i);
        }

        // 遍历列表，打印每个弱引用的对象
        for (var ref : list) {
            System.out.println(ref.get());
        }

        // 设置一个强引用，防止最后一个字节数组被垃圾回收
        var last = list.getLast().get();

        // 手动执行一次gc，触发垃圾回收
        System.gc();
        System.out.println("------------------------------");
        // 再次遍历列表，打印每个弱引用的对象，观察垃圾回收后的效果
        for (var ref : list) {
            System.out.println(ref.get());
        }

    }
}

