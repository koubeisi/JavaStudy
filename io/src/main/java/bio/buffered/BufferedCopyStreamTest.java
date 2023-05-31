package bio.buffered;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 实现二进制文件拷贝
 *
 * @author KouBeisi
 * @since 2021/8/25
 */
public class BufferedCopyStreamTest {

    @Test
    void test() {
        final String srcFilePath = "E:/Temp/L04IP协议（3）-IP编址.pdf";
        final String destFilePath = "E:/Temp/IP编址.pdf";

        try (final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(destFilePath));
             final BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(srcFilePath))
        ) {
            byte[] buf = new byte[1024];
            int length;
            while ((length = bufferedInputStream.read(buf)) > -1){
                bufferedOutputStream.write(buf,0,length);
            }
            System.out.println("Finished!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
