package bio.file;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 文件输入流和文件输出流
 *
 * @author KouBeisi
 * @since 2021/8/23
 */
public class FileStreamTest {

    @Test
    void fileOutputStream() {
        try (final FileOutputStream fileOutputStream = new FileOutputStream("E:\\Temp\\" + "a.txt")) {
            fileOutputStream.write("Hello World".getBytes());
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void fileInputStream() {
        try (final InputStream inputStream = ClassLoader.getSystemResourceAsStream("b.txt")) {
            assert inputStream != null;
            final byte[] bytes = inputStream.readAllBytes();
            System.out.println(new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void fileInputStream1() {
        try (final InputStream inputStream = ClassLoader.getSystemResourceAsStream("b.txt")) {
            assert inputStream != null;
            byte[] buf = new byte[1024];
            int length;
            while ((length = inputStream.read(buf)) > -1) {
                System.out.println(new String(buf,0,length));
            }
//            inputStream.read()
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    void fileInputStream2() {
        try (final InputStream inputStream = ClassLoader.getSystemResourceAsStream("b.txt")) {
            assert inputStream != null;

            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            char[] buf = new char[1024];
            int length;
            while ((length = reader.read(buf)) > -1) {
                System.out.println(new String(buf,0,length));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
