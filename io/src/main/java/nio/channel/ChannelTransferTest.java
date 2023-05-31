package nio.channel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Channel 之间的传递
 * @author KouBeisi
 * @since 2021/10/19
 */
public class ChannelTransferTest {

    @Test
    void test(){

        try (final RandomAccessFile file01 = new RandomAccessFile("file/channel/01.txt", "rw");
             final FileChannel fromChannel = file01.getChannel();

             final RandomAccessFile file02 = new RandomAccessFile("file/channel/02.txt", "rw");
             final FileChannel toChannel = file02.getChannel()
        ) {

            long position = 0;
            final long size = fromChannel.size();
            final long actual = toChannel.transferFrom(fromChannel, position, size);
            System.out.println("size:" + size + "\nactual:" + actual);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
