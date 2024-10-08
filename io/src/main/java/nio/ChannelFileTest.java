package nio;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class ChannelFileTest {

    /*
     * 1.利用通道(非直接缓冲区)完成文件的复制
     */
    @Test
    public void test() {
        // 1）获取通道
        try (var channel1 = Files.newByteChannel(Paths.get("file", "channel", "01.txt"));
             var channel2 = Files.newByteChannel(Paths.get("file", "channel", "02.txt"),
                     StandardOpenOption.WRITE,       // 确保有写权限
                     StandardOpenOption.CREATE,      // 如果文件不存在则创建
                     StandardOpenOption.TRUNCATE_EXISTING // 如果文件存在则清空
             )
        ) {

            // 2）分配指定大小的缓冲区 ??文件的大小比缓冲区大？？
            ByteBuffer buf = ByteBuffer.allocate(8);

            // 3）将通道中的数据存入缓冲区中
            while (channel1.read(buf) != -1) {
                buf.flip(); // 切换成读取数据的模式
                // 4）将缓冲区中的数据写入通道中
                System.out.println(StandardCharsets.UTF_8.decode(buf));
                channel2.write(buf);
                buf.clear(); // 清空缓冲区
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    /*
     * 使用直接缓冲区完成文件的复制（内存映射文件）
     */
    @Test
    public void test2() throws IOException {

        long start = System.currentTimeMillis();

        // 打开通道
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.READ,
                StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);

        // 内存映射文件
        MappedByteBuffer inMappedBuf = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());

        // 直接对缓冲区进行数据的读写
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);

        inChannel.close();
        outChannel.close();

        long end = System.currentTimeMillis();
        System.out.println("耗费时间为：" + (end - start) + "ms");
    }

    /*
     * 通道之间的数据传输（直接缓冲区）
     * 底层利用操作系统的【零拷贝】进行优化
     */
    @Test
    public void test3() {

        // 打开通道
        try (FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
             FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.READ,
                     StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)) {
            long size = inChannel.size();
            var left = size;
            while (left > 0) {
                // 默认限制传输 2G
                left = left - inChannel.transferTo(size - left, left, outChannel);
            }

//        outChannel.transferFrom(inChannel, 0, inChannel.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void test4() throws IOException {

        RandomAccessFile raf1 = new RandomAccessFile("Java.txt", "rw");

        // 1.获取通道
        FileChannel channel1 = raf1.getChannel();

        // 2.分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(10240);

        // 3.分散读取
        ByteBuffer[] bufs = {buf1, buf2};
        channel1.read(bufs);

        for (ByteBuffer byteBuffer : bufs) {
            byteBuffer.flip();
        }

        // 输出buffer中的内容
        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

        // 4.聚集写入
        RandomAccessFile raf2 = new RandomAccessFile(new File("jav.txt"), "rw");
        FileChannel channel2 = raf2.getChannel();

        channel2.write(bufs);

        channel1.close();
        channel2.close();
        raf1.close();
        raf2.close();
    }

    /*
     * 等同于test4()
     */
    @Test
    public void test5() throws IOException {

        // 1.获取通道
        FileChannel inChannel = FileChannel.open(Paths.get("Java.txt"), StandardOpenOption.READ);

        // 2.分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(10240);

        // 3.分散读取
        ByteBuffer[] bufs = {buf1, buf2};
        inChannel.read(bufs);

        for (ByteBuffer byteBuffer : bufs) {
            byteBuffer.flip();
        }

        // 输出buffer中的内容
        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

        // 4.聚集写入
        FileChannel outChannel = FileChannel.open(Paths.get("ja.txt"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        outChannel.write(bufs);

    }


}
