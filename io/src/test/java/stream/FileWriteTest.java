package stream;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Description
 * @Author KouBeisi
 * @Date 2019/12/2 0:46
 * @Version 1.0
 **/
public class FileWriteTest {

    @Test
    void test1(){
        File file = new File("hello1.txt");
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(1 + 48);
            writer.write("I love Java.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
