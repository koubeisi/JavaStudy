package bio.buffered;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * BufferedReader 和 BufferedWriter 是字符操作，不要去操作一个二进制文件，可能造成文件损坏
 *
 * @author KouBeisi
 * @since 2021/8/25
 */
public class BufferedCopyTest {

    @Test
    void copy() {

        final String srcFilePath = "E:/Temp/a1.txt";
        final String destFilePath = "E:/Temp/b1.txt";

        try (final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(destFilePath));
             final BufferedReader bufferedReader = new BufferedReader(new FileReader(srcFilePath))
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null){
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            System.out.println("Finished");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
