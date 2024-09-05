package nio.channel;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class SocketChannelTest {

    private static final int PORT = 9898;

    @Test
    void test() {
        // 1. 打开服务端通道
        try (final var serverSocketChannel = ServerSocketChannel.open()) {
            // 2. 绑定对应的端口号
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            // 3. 通道默认是阻塞的，需要设置为非阻塞🥶
            serverSocketChannel.configureBlocking(true);
            log.info("{}", "服务端启动成功......");
            while (true) {
                log.info("{}", "Waiting for connection");
                // 4. 检查是否有客户端连接 有客户端连接会返回对应的通道
                final var socketChannel = serverSocketChannel.accept();
                if (socketChannel == null) {
                    log.info("{}", "没有客户端连接，我去做别的事情");
                    Thread.sleep(2000);
                    continue;
                }
                log.info("{}", "Incoming connection from: " + socketChannel.socket().getRemoteSocketAddress());
                // 5. 获取客户端传递过来的数据并把数据放在 ByteBuffer 这个缓冲区中
                final ByteBuffer buffer = ByteBuffer.allocate(1024);
                // 正数：标示本地读到有效字节数；0，标示本次没有督导数据；-1，标示读到末尾
                if (socketChannel.read(buffer) != -1) { // 阻塞方法🥶
                    buffer.flip();
                    log.info("客户端消息：{}", StandardCharsets.UTF_8.decode(buffer));
                    buffer.clear();
                }
                // 6. 给客户端回写数据
                socketChannel.write(ByteBuffer.wrap("我是服务端！".getBytes(StandardCharsets.UTF_8)));
                socketChannel.close();
            }
        } catch (InterruptedException | IOException e) {
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            log.info("{}", e.getMessage());
        }


    }

    @Test
    void socketChannel() {
        // 1. 打开通道
        // 2. 设置连接IP和端口号
        try (final SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", PORT))) {
            // 3. 写出数据
            socketChannel.write(ByteBuffer.wrap("我是客户端".getBytes(StandardCharsets.UTF_8)));
            // 4. 读取服务端写回的数据
            final ByteBuffer buffer = ByteBuffer.allocate(1024);
            var read = socketChannel.read(buffer);
            log.info("{}", "服务端消息：" + new String(buffer.array(), 0, read, StandardCharsets.UTF_8));
        } catch (IOException e) {
            log.info("{}", e.getMessage());
        }

    }

}
