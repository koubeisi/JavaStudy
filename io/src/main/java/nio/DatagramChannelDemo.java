package nio;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.Scanner;

public class DatagramChannelDemo {

    @Test
    public void client() throws IOException {
        
        DatagramChannel dChannel = DatagramChannel.open();
        
        dChannel.configureBlocking(false);
        
        ByteBuffer buf = ByteBuffer.allocate(1024);
        
        Scanner scanner = new Scanner(System.in);
        
        while(scanner.hasNext()) {
            buf.put((LocalTime.now().toString()+":" +scanner.nextLine()).getBytes());
            buf.flip();
            dChannel.send(buf, new InetSocketAddress("127.0.0.1",8989));
            buf.clear();
        }
        
        scanner.close();
        dChannel.close();
        
    }
    
    @Test
    public void server() throws IOException {
        DatagramChannel dChannel = DatagramChannel.open();
        
        dChannel.configureBlocking(false);
        
        dChannel.bind(new InetSocketAddress(8989));
        
        Selector selector = Selector.open();
        
        dChannel.register(selector, SelectionKey.OP_READ);
        
        while(selector.select()>0) {
            Iterator<SelectionKey> interator = selector.selectedKeys().iterator();
            
            while(interator.hasNext()) {
                SelectionKey key=interator.next();
                if(key.isReadable()) {
                    ByteBuffer buf=ByteBuffer.allocate(1024);
                    dChannel.receive(buf);
                    buf.flip();
                    System.out.println(new String(buf.array(),0,buf.limit()));
                    buf.clear();
                }
            }
        }
    }
}
