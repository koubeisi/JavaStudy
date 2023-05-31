package nio.channel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author KouBeisi
 * @since 2021/10/19
 */
public class SocketChannelTest {

    private static final int PORT = 9898;

    @Test
    void test() throws IOException, InterruptedException {
        // 1. 打开服务端通道
        final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 2. 绑定对应的端口号
        serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
        // 3. 通道默认是阻塞的，需要设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        System.out.println("服务端启动成功......");

        while (true) {
            System.out.println("Waiting for connection");
            // 4. 检查是否有客户端连接 有客户端连接会返回对应的通道
            final SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel == null) {
                System.out.println("没有客户端连接，我去做别的事情");
                Thread.sleep(2000);
                continue;
            }
            System.out.println("Incoming connection from: " + socketChannel.socket().getRemoteSocketAddress());
            // 5. 获取客户端传递过来的数据并把数据放在 ByteBuffer 这个缓冲区中
            final ByteBuffer buffer1 = ByteBuffer.allocate(1024);
            // 正数：标示本地读到有效字节数；0，标示本次没有督导数据；-1，标示读到末尾
            var read = socketChannel.read(buffer1);
            System.out.println("客户端消息：" + new String(buffer1.array(), 0, read, StandardCharsets.UTF_8));
            // 6. 给客户端回写数据
            socketChannel.write(ByteBuffer.wrap("我是服务端！".getBytes(StandardCharsets.UTF_8)));
            socketChannel.close();

        }
    }

    @Test
    void socketChannel() throws IOException {
        // 1. 打开通道
        // 2. 设置连接IP和端口号
        final SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", PORT));
        // 3. 写出数据
        socketChannel.write(ByteBuffer.wrap("我是客户端".getBytes(StandardCharsets.UTF_8)));
        // 4. 读取服务端写回的数据
        final ByteBuffer buffer = ByteBuffer.allocate(1024);
        var read = socketChannel.read(buffer);
        System.out.println("服务端消息：" + new String(buffer.array(), 0, read, StandardCharsets.UTF_8));
        // 5. 释放资源
        socketChannel.close();
    }

}
