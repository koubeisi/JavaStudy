package bio.buffered;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author KouBeisi
 * @since 2021/8/25
 */
public class BufferedReaderTest {

    @Test
    void test() {

        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader("E:/Temp/a.txt"));
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
