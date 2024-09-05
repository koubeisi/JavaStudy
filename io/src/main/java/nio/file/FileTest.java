package nio.file;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * File 工具类 Files 的使用
 *
 * @author KouBeisi
 * @since 2021/8/26
 */
public class FileTest {

    /**
     * 创建文件
     */
    @Test
    void createFile() throws IOException {
        // 创建路径
        final Path path = Paths.get("E:\\Temp\\hello.txt");
        // 判断是否存在
        final boolean exists = Files.exists(path);
        // 不存在则创建文件
        if (!exists) {
            final Path file = Files.createFile(path);
            System.out.println(file);
        } else {
            System.out.println("文件已存在");
        }

    }

    /**
     * 创建目录
     */
    @Test
    void createDire() throws IOException {
        final Path path = Paths.get("E:\\Temp\\tem\\tes");
        if (!Files.exists(path)) {
            final Path directories = Files.createDirectories(path);
            System.out.println(directories);
        } else {
            System.out.println("目录已存在");
        }
    }

    /**
     * 移动文件
     * 注意：被移动到的目录必须存在
     */
    @Test
    void moveFile() throws IOException {
        // 被移动的文件
        final Path source = Paths.get("e:\\Temp\\hello.txt");
        // 移动到的目的地
        final Path target = Paths.get("e:\\Temp\\temp\\HHello.txt");

        Files.move(source, target);
    }

    /**
     * 拷贝文件
     */
    @Test
    void copyFile() throws IOException {
        // 被拷贝的文件
        final Path source = Paths.get("e:\\Temp\\hello.txt");
        // 拷贝目的地
        final Path target = Paths.get("e:\\Temp\\temp\\HHllo.txt");

        Files.copy(source, target);
    }

    @Test
    void deleteFile() throws IOException {
        // 被删除的文件
        final Path source = Paths.get("F:\\dump\\logback_info.log.2023-08-04-0");
        Files.delete(source);
    }

    /**
     * 遍历文件树
     * 方便删除
     */
    @Test
    @DisplayName("遍历文件树")
    void walkFileTree() throws IOException {
        AtomicInteger dirCount = new AtomicInteger();
        AtomicInteger fileCount = new AtomicInteger();
        AtomicInteger jarCount = new AtomicInteger();
        Files.walkFileTree(Paths.get("E:\\Program\\jdk-21.0.3+9"), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("目录：" + dir);
                dirCount.incrementAndGet();
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("文件：" + file);
                fileCount.incrementAndGet();
                if (file.toString().endsWith(".jar")) {
                    jarCount.incrementAndGet();
                }
                return FileVisitResult.CONTINUE;
            }
        });

        System.out.println("目录：" + dirCount);
        System.out.println("文件：" + fileCount);
        System.out.println("jar：" + jarCount);
    }

    @Test
    @DisplayName("遍历文件树2")
    void walk() throws IOException {
        AtomicInteger dirCount = new AtomicInteger();
        AtomicInteger fileCount = new AtomicInteger();
        AtomicInteger jarCount = new AtomicInteger();
        try (var paths = Files.walk(Paths.get("E:\\Program\\jdk-21.0.3+9"))) {
            paths.forEach(path -> {
                if (Files.isDirectory(path)) {
                    System.out.println("目录：" + path);
                    dirCount.incrementAndGet();
                } else if (Files.isRegularFile(path)) {
                    System.out.println("文件：" + path);
                    fileCount.incrementAndGet();
                    if (path.toString().endsWith(".jar")) {
                        jarCount.incrementAndGet();
                    }
                }
            });
        }
        System.out.println("目录：" + dirCount);
        System.out.println("文件：" + fileCount);
        System.out.println("jar：" + jarCount);
    }

}
