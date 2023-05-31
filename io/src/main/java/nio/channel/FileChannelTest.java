package nio.channel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author KouBeisi
 * @since 2021/10/18
 */
public class FileChannelTest {

    /**
     * FileChannel 读取数据到 Buffer
     */
    @Test
    void read() throws IOException {

        try (// 创建 FileChannel
             final RandomAccessFile file = new RandomAccessFile("file/channel/01.txt", "rw");
             final FileChannel channel = file.getChannel();
        ) {
            // 创建 Buffer
            final ByteBuffer buffer = ByteBuffer.allocate(1024);

            // 读取数据到 Buffer
            int read = channel.read(buffer);
            while (read != -1) {
                System.out.println("读取了：" + read);
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.clear();
                read = channel.read(buffer);
            }
            System.out.println("结束了");
        }

    }

    /**
     * FileChannel 写入数据到 Buffer
     */
    @Test
    void write() {
        try (// 创建 FileChannel
             final RandomAccessFile file = new RandomAccessFile("file/channel/02.txt", "rw");
             final FileChannel channel = file.getChannel();
        ) {
            // 创建 Buffer
            final ByteBuffer buffer = ByteBuffer.allocate(1024);

            // 写入数据到 Buffer
            String data = "12345qwe";
            buffer.clear();
            buffer.put(data.getBytes());

            buffer.flip();
            while (buffer.hasRemaining()) {
                channel.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
