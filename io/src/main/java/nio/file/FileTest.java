package nio.file;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
            final Path file = Files.createFile(Paths.get("E:\\Temp\\hello.txt"));
            System.out.println(file);
        } else {
            System.out.println("文件已存在");
        }

    }

    /**
     * 创建目录
     *
     */
    @Test
    void createDire() throws IOException {
        final Path path = Paths.get("E:\\Temp\\tem\\tes");
        if (!Files.exists(path)){
            final Path directories = Files.createDirectories(path);
            System.out.println(directories);
        } else {
            System.out.println("目录已存在");
        }
    }

    /**
     * 移动文件
     * 注意：被移动到的目录必须存在
     *
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

        Files.copy(source,target);
    }


}
