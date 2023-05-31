package nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


/**
 * 利用SocketChannel示例 Blocking NIO
 * @author koubeisi
 * @date 2019年3月27日下午10:26:11
 * @version v1.0
 */
public class SocketChannelTest {

    /**
     * 客户端
     */
    @Test
    public void client() throws IOException {
        // 1.建立通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        FileChannel fChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);

        // 2.分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 3.读取本地文件,发送到服务端
        while (fChannel.read(buf) != -1) {
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }

        // 4.关闭通道
        sChannel.close();
        fChannel.close();

    }

    /*
     * 服务端
     */
    @Test
    public void server() throws IOException {
        // 1.建立通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        FileChannel fChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE,
                StandardOpenOption.CREATE);

        // 2.绑定端口号
        ssChannel.bind(new InetSocketAddress(9898));

        // 3.获取客户端连接的通道
        SocketChannel sChannel = ssChannel.accept();

        // 4.分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 5.接受客户端的数据,并保存到本地
        while (sChannel.read(buf) != -1) {
            buf.flip();
            fChannel.write(buf);
            buf.clear();
        }

        //6.关闭通道
        sChannel.close();
        ssChannel.close();
        fChannel.close();
    }

    /*
     * client1()和server1()用来测试数据的发送和返回
     */
    @Test
    public void client1() throws IOException {
     // 1.建立通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        FileChannel fChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);

        // 2.分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 3.读取本地文件,发送到服务端
        while (fChannel.read(buf) != -1) {
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }

        // 4.数据发送完毕
        sChannel.shutdownOutput();

        // 5.接受服务的的反馈
        int length =0;
        while((length = sChannel.read(buf)) != -1) {
            buf.flip();
            System.out.println(new String(buf.array(),0,length));
            buf.clear();
        }

        // 6.关闭通道
        sChannel.close();
        fChannel.close();
    }

    @Test
    public void server1() throws IOException {
     // 1.建立通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        FileChannel fChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE,
                StandardOpenOption.CREATE);

        // 2.绑定端口号
        ssChannel.bind(new InetSocketAddress(9898));

        // 3.获取客户端连接的通道
        SocketChannel sChannel = ssChannel.accept();

        // 4.分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 5.接受客户端的数据,并保存到本地
        while (sChannel.read(buf) != -1) {
            buf.flip();
            fChannel.write(buf);
            buf.clear();
        }

        // 6.发送反馈给客户端
        buf.put("服务端返回的数据".getBytes());
        buf.flip();
        sChannel.write(buf);

        // 7.关闭通道
        sChannel.close();
        fChannel.close();
        ssChannel.close();

    }

}
