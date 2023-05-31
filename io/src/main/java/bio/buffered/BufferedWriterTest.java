package bio.buffered;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author KouBeisi
 * @since 2021/8/25
 */
public class BufferedWriterTest {

    /**
     * 以覆盖的方式写入文件
     */
    @Test
    void test(){

       try( final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("E:/Temp/b.txt"))){
           bufferedWriter.write("Hello Java!");
           bufferedWriter.newLine();
           bufferedWriter.write("Hello Golang");
       } catch (IOException e) {
           e.printStackTrace();
       }

    }

    /**
     * 以追加的方式写入文件
     */
    @Test
    void append(){
        try( final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("E:/Temp/b.txt",true))){
            bufferedWriter.write("\n");
            bufferedWriter.write("Hello JavaScript");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
