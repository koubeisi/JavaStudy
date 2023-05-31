package file;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class FileTest {

    @Test
    void test1(){
        File file1 = new File("file1");
        if (!file1.isDirectory()) {
            System.out.println("file1不是文件夹");
            file1.mkdir();
        }
        //返回构造方法传入的路径
        System.out.println(file1.getPath());
        //返回绝对路径
        System.out.println(file1.getAbsolutePath());
        try {
            //返回规范路径
            System.out.println(file1.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test2(){
        File file2 = new File("parentPath","file2");
    }

    @Test
    void test3(){
        File file1 = new File("file1");
        File file3 = new File(file1,"file3");
        System.out.println(file3);
    }
}