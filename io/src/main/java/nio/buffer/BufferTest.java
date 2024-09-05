package nio.buffer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;


/**
 * 测试NIO中Buffer类的基本功能
 *
 * @author koubeisi
 * @since 2019年3月27日上午6:54:07
 */
public class BufferTest {

    @Test
    @DisplayName("Buffer的创建")
    public void create() {
        // 1. 创建指定长度的 HeapByteBuffer
        var heapBuffer = ByteBuffer.allocate(5);
        for (int i = 0; i < 5; i++) {
            // 从缓冲区中去拿数据，只能拿5次，否则就会报错
            heapBuffer.put((byte) (i * 2));
        }
        // 翻转缓冲区，准备读取
        heapBuffer.flip();
        // 读取数据并输出
        while (heapBuffer.hasRemaining()) {
            System.out.print(heapBuffer.get() + " ");
        }
        System.out.println();


        // 2. 创建一个有内容的缓冲区
        var buffer1 = ByteBuffer.wrap("abc".getBytes(StandardCharsets.UTF_8));
        for (int i = 0; i < 3; i++) {
            System.out.println(buffer1.get());
        }
    }

    /**
     * Buffer的基本功能
     */
    @Test
    @DisplayName("Buffer的基本使用")
    public void basic() {
        //1、分配一个制定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        System.out.println("--------allocate()-----");
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());

        //2、利用put()存入数据到缓冲区中
        String str = "abcde";
        byteBuffer.put(str.getBytes());

        System.out.println("----------put()----------");
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("remaining:" + byteBuffer.remaining());

        //3、切换到读取数据的模式
        byteBuffer.flip();
        System.out.println("----------flip()--------");
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("remaining:" + byteBuffer.remaining());

        //4、读取缓冲区中的数据
        byte[] dst = new byte[byteBuffer.limit()];

        System.out.println("----------get()----------");
        System.out.println("before get position:" + byteBuffer.position());
        byteBuffer.get(dst);
        System.out.println(new String(dst));
        System.out.println("after get position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("remaining:" + byteBuffer.remaining());

        //5、rewind()：可重复读数据
        byteBuffer.rewind();

        System.out.println("--------rewind()------");
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("remaining:" + byteBuffer.remaining());

        //6、清空缓冲区。但是缓冲区内的数据还在，只是数据处于被遗忘状态
        byteBuffer.clear();
        System.out.println("-------------------------");
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("remaining:" + byteBuffer.remaining());

        System.out.println((char) byteBuffer.get(0));
    }

    /**
     * mark的基本功能
     */
    @Test
    @DisplayName("mark的使用")
    public void mark() {
        String str = "abcde";

        ByteBuffer buf = ByteBuffer.allocate(1024);

        buf.put(str.getBytes());

        buf.flip();

        byte[] dst = new byte[buf.limit()];
        buf.get(dst, 0, 2);
        System.out.println("position:" + buf.position());
        System.out.println(new String(dst));

        //mark():标记
        buf.mark();

        buf.get(dst, 2, 2);
        System.out.println("position:" + buf.position());
        System.out.println(new String(dst, 2, 2));
        System.out.println(new String(dst));

        //reset()：恢复到标记
        buf.reset();
        System.out.println("position：" + buf.position());

        //是否还有剩余的数据，如果有获取其数量
        if (buf.hasRemaining()) {
            System.out.println(buf.remaining());
        }

    }

    /**
     * 创建直接缓冲区
     */
    @Test
    public void test3() {
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);
        //判断是否是直接缓冲区
        System.out.println(buf.isDirect());
    }


    @Test
    @DisplayName("分散读")
    public void scatteringReads() {
        try (var raf = new RandomAccessFile(Paths.get("file", "channel", "words.txt").toFile(), "r");
             var channel = raf.getChannel()) {
            var bf1 = ByteBuffer.allocate(3);
            var bf2 = ByteBuffer.allocate(3);
            var bf3 = ByteBuffer.allocate(3);
            var bfs = new ByteBuffer[]{bf1, bf2, bf3};
            channel.read(bfs);
            bf1.flip();
            bf2.flip();
            bf3.flip();
            System.out.println(StandardCharsets.UTF_8.decode(bf1));
            System.out.println(StandardCharsets.UTF_8.decode(bf2));
            System.out.println(StandardCharsets.UTF_8.decode(bf3));
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    @Test
    @DisplayName("聚集写")
    public void gatheringWrite() {
        try (var raf = new RandomAccessFile(Paths.get("file", "channel", "words.txt").toFile(), "rw");
             var channel = raf.getChannel()) {

            var bf1 = StandardCharsets.UTF_8.encode("hello");
            var bf2 = StandardCharsets.UTF_8.encode("world");
            var bf3 = StandardCharsets.UTF_8.encode("你好");

            var bfs = new ByteBuffer[]{bf1, bf2,bf3};
            channel.write(bfs);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
