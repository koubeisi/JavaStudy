package nio;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


/**
 * 示范Selector的使用，示范Non-Blocking IO
 *
 * @author koubeisi
 * @version v1.0
 * @since 2019年3月29日下午3:47:21
 */
@Slf4j
public class SelectorTest {

    @Test
    public void client() {
        // 1.建立通道，并绑定对应的端口号
        try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898))) {
            // 3.建立缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);
            // 4.发送数据给服务器
            buf.put("我是客户端".getBytes());
            buf.flip();
            socketChannel.write(buf);
            buf.clear();
            // 5.读取服务端消息
            var read = socketChannel.read(buf);
            buf.flip();
            System.out.println("服务端消息：" + new String(buf.array(), 0, read, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void server() {
        // 1.建立通道
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            // 2.切换为非阻塞模式
            serverSocketChannel.configureBlocking(false);
            // 3.绑定端口
            serverSocketChannel.bind(new InetSocketAddress(9898));
            // 4.获取选择器
            Selector selector = Selector.open();
            // 5.将通道注册到选择器，并且指定“监听连接事件”
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务端启动成功......");

            while (true) {
                // 6.轮询式的检查选择器上已经有事件
                var select = selector.select(2000);
                if (select == 0) {
                    System.out.println("没有事件发生......");
                    continue;
                }

                // 7.获取当前选择器中所有注册的“选择键”
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                // 8.获取准备就绪的事件
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();

                    // 9.判断具体是生命事件准备就绪
                    if (key.isAcceptable()) {
                        // 10.若“接收就绪”，获取客户端连接
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        System.out.println("有客户端连接......");
                        // 11.切换非阻塞模式
                        socketChannel.configureBlocking(false);
                        // 12.将该通道注册到选择器上，监听读就绪事件
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isConnectable()) {
                        // a connection was established with a remote server.
                    } else if (key.isReadable()) {
                        try {
                            // 13.获取当前选择器上“读就绪”状态通道
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            // 14.读取数据
                            ByteBuffer buf = ByteBuffer.allocate(1024);
                            if (socketChannel.read(buf) > 0) {
                                buf.flip();
                                System.out.println("客户端消息：" + new String(buf.array(), 0, buf.limit()));
                                buf.clear();
                                buf.put("我是服务端".getBytes(StandardCharsets.UTF_8));
                                buf.flip();
                                socketChannel.write(buf);
                                if (buf.hasRemaining()) {
                                    key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
                                    key.attach(buf);
                                }
                            }
                        } catch (IOException e) {
                            log.info("{}", e.getMessage());
                            key.cancel();
                        }

                    } else if (key.isWritable()) {
                        // a channel is ready for writing
                        var buffer = (ByteBuffer) key.attachment();
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        var write = socketChannel.write(buffer);
                        log.info("{}", write);
                        if (!buffer.hasRemaining()) {
                            key.attach(null);
                            key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);
                        }

                    }
                    // 从集合中删除对应的事件，防止二次处理
                    keyIterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
