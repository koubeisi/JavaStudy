package org.example.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceDemo {

    public static void main(String[] args) throws InterruptedException {

        /************* SoftReference **************/
        User u1 = new User("01", "Tony");
        SoftReference<User> sr = new SoftReference<User>(u1);

        System.out.println(sr.get());
        u1 = null;
        System.gc();
        System.out.println(sr.get()); // 软引用只会在OOM时是gc的回收对象

        /************* WeakReference **************/
        User u2 = new User("02", "Jeck");
        WeakReference<User> wr = new WeakReference<User>(u2);

        System.out.println(wr.get());
        u2 = null;
        System.gc();
        System.out.println(wr.get()); // 弱引用是gc的回收对象，只要发现立即回收

        /************* WeakReference **************/
        User u3 = new User("03", "Marry");
        ReferenceQueue<? super User> queue = new ReferenceQueue<User>();
        PhantomReference<User> pr = new PhantomReference<User>(u3, queue);
        
        //虚引用随时都有可能会被gc回收，虚引用的get方法去尝试获得强引用对象时总是会失败，
        //并且他必须和引用队列一起使用，用于跟踪垃圾回收过程
        System.out.println(pr.get());  
        
        
    }

}

class User {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "id:" + id + " name:" + name;
    }
}
