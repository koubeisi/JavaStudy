package nio;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class CharsetDemo {

    /*
     * 字符集
     */
    @Test
    public void test6() {
        Map<String,Charset> map = Charset.availableCharsets();
        Set<Entry<String,Charset>> set = map.entrySet();
        
        for(Entry<String,Charset> entry : set) {
            System.out.println(entry.getKey() + "="+entry.getValue());
        }
    }
    
    /*
     * 利用解码器和编码器在char和byte之间互相转换
     */
    @Test
    public void test7() throws CharacterCodingException {
        Charset cs1 = Charset.forName("GBK");
        
        //获取编码器
        CharsetEncoder ce =cs1.newEncoder();
        //获取解码器
        CharsetDecoder cd=cs1.newDecoder();
        
        //在CharBuffer中输入一段中文
        CharBuffer cBuf = CharBuffer.allocate(1024);
        cBuf.put("中华人民共和国");
        cBuf.flip();
        
        //编码
        ByteBuffer bBuf = ce.encode(cBuf);
        for(int i=0;i<bBuf.limit();i++) {
            System.out.println(bBuf.get());
        }
        
        //解码
        bBuf.flip();
        CharBuffer result = cd.decode(bBuf);
        System.out.println(result.toString());
        
        //UTF-8解GBK编码
        Charset cs2 = Charset.forName("UTF-8");
        bBuf.flip();
        CharBuffer result1 = cs2.decode(bBuf);
        System.out.println(result1.toString());
        
                
    }
}
