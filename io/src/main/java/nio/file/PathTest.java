package nio.file;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author KouBeisi
 * @since 2021/8/26
 */
public class PathTest {

    @Test
    void test() {

        final Path path = Paths.get("e:", "Temp", "a.txt");
        // 获取文件名
        System.out.println(path.getFileName());
        // 获取根路径
        System.out.println(path.getRoot());
        // 获取名称元素数量
        System.out.println(path.getNameCount());

    }
}
