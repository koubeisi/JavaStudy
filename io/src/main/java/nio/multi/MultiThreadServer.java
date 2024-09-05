package nio.multi;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author PC
 * @since 2024/9/6
 */
@Slf4j
@SuppressWarnings("all")
public class MultiThreadServer {

    @Test
    void server() {
        Thread.currentThread().setName("main");

        try (var ssc = ServerSocketChannel.open()) {
            ssc.configureBlocking(false);
            var mainSelector = Selector.open();
            ssc.register(mainSelector, SelectionKey.OP_ACCEPT);
            ssc.bind(new InetSocketAddress(8888));
            // 1. 创建固定数量的 worker 并初始化
            Worker[] workers = new Worker[Runtime.getRuntime().availableProcessors()];
            for (int i = 0; i < workers.length; i++) {
                workers[i] = new Worker("worker-" + i);
            }
            AtomicInteger index = new AtomicInteger();
            while (true) {
                mainSelector.select();
                var keyIterator = mainSelector.selectedKeys().iterator();
                while (keyIterator.hasNext()) {
                    var key = keyIterator.next();
                    keyIterator.remove();
                    if (key.isAcceptable()) {
                        var sc = ssc.accept();
                        sc.configureBlocking(false);
                        log.info("connected...{}", sc.getRemoteAddress());
                        // 2. 关联 selector
                        log.info("before register...{}", sc.getRemoteAddress());
                        workers[index.getAndIncrement() % workers.length].register(sc);
                        log.info("after register...{}", sc.getRemoteAddress());
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    static class Worker implements Runnable {
        /**
         * worker 线程内部的 selector，和 main selector 分离
         */
        private Selector selector;
        private Thread thread;
        private String name;
        private volatile boolean start = false;
        private ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue<>();

        public Worker(String name) {
            this.name = name;
        }

        public void register(SocketChannel sc) throws IOException {
            if (!start) {
                selector = Selector.open();
                thread = new Thread(this, name);
                thread.start();
                start = true;
            }

//            queue.add(() -> {
//                try {
//
//                } catch (ClosedChannelException e) {
//                    log.info("{}", e.getMessage());
//                }
//            });
            sc.register(selector, SelectionKey.OP_READ);
            selector.wakeup();

        }

        @Override
        public void run() {
            while (true) {
                try {
                    selector.select();
//                    var run = queue.poll();
//                    if (run != null) {
//                        run.run();
//                    }
                    var keyIterator = selector.selectedKeys().iterator();
                    while (keyIterator.hasNext()) {
                        var key = keyIterator.next();
                        keyIterator.remove();
                        if (key.isReadable()) {
                            var buffer = ByteBuffer.allocate(16);
                            var sc = (SocketChannel) key.channel();
                            log.info("read...{}", sc.getRemoteAddress());
                            var read = sc.read(buffer);
                            if (read == -1) {
                                log.info("client closed connection");
                                sc.close();
                                continue; // 跳过这次循环
                            }
                            buffer.flip();
                            log.info(StandardCharsets.UTF_8.decode(buffer).toString());
                            buffer.clear();
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    @Test
    void client() {
        try (var sc = SocketChannel.open()) {
            sc.connect(new InetSocketAddress("localhost", 8888));
            sc.write(ByteBuffer.wrap("hello world".getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
