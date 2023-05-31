package stream;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Description
 * @Author KouBeisi
 * @Date 2019/12/1 23:26
 * @Version 1.0
 **/
public class FileReaderTest {

    @Test
    void test1(){

        //1.实例化File类的对象，指明要操作的对象
        File file = new File("hello.txt");
        System.out.println(file.getAbsolutePath());
        //2.提供具体的流
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            int data = reader.read();
            //3.数据的读入
            while (data != -1){
                System.out.print((char)data);
                data = reader.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null){
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
