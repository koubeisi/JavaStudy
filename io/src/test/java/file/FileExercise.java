package file;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

/**
 * @description
 * @author KouBeisi
 * @date 2019/12/1 16:11
 * @version 1.0
 **/
public class FileExercise {

    /**
     * 1. 利用 File 构造器， new 一个文件目录 file
     * 1) 在其中创建多个文件和目录
     * 2) 编写方法，实现删除 file 中指定文件的操作
     */
    @Test
    void test1(){
        File file1 = new File("file1");
        //create a directory in file1
        File dir1 = new File(file1,"dir1");
        if (!dir1.isDirectory()){
            dir1.mkdir();
        }
        //create a file in file1
        File file2 = new File(file1,"file2");
        if (!file2.isFile()){
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        file2.delete();
    }

    /**
     * 2. 判断指定目录下是否有后缀名为.jpg的文件，如果有，就输出该文件名称
     */
    @Test
    void test2(){
        File file1 = new File("file1");

        //使用FileFilter
        FileFilter fileFilter = new FileFilter() {
            //如果File是文件，且后缀为.jpg返回为true
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile() && pathname.toString().endsWith(".jpg") ;
            }
        };

        File[] files = file1.listFiles(fileFilter);
        for (File file : files) {
            System.out.println(file);
        }
    }

    /**
     * 3. 遍历指定目录所有文件名称，包括子文件目录中的文件。
     * 拓展1：并计算指定目录占用空间的大小
     * 拓展2：删除指定文件目录及其下的所有文件
     */
    @Test
    void test3(){
        File file1 = new File("file1");
        System.out.println(file1.length());
        listFile(file1);
        System.out.println(sumFile(file1));

    }

    //递归输出子目录及目录中的子文件
    void listFile(File filePath){

        File[] files = filePath.listFiles();

        for (File file : files) {
            if (file.isDirectory()){
                System.out.println(file.toString());
                listFile(file);
            } else {
                System.out.println(file.toString());
            }
        }
    }

    //计算大小
    int sumFile(File filePath){

        int sum = 0;

        File[] files = filePath.listFiles();

        for (File file : files) {
            if (file.isDirectory()){
                sum += sumFile(file);
            } else {
                sum += file.length();
            }
        }

        return sum;
    }
}
