package com.koubs.socket.file;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author KouBeisi
 * @since 2021/8/23
 */
public class Client {

    public static void main(String[] args) {
        try ( // 1.请求与服务端的 Socket 连接
              final Socket socket = new Socket("127.0.0.1", 8888);
              // 2.把字节输出流包装成数据输出流
              final DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        ) {
            // 3.先发送上传文件的后缀给服务端
            dataOutputStream.writeUTF(".png");
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("kou.png");
            if (inputStream != null) {
                byte[] buf = new byte[1024];
                int len;
                while ((len = inputStream.read(buf)) > 0) {
                    dataOutputStream.write(buf, 0, len);
                }
            }
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
