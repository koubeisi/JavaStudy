package xuetang;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestBufferedOutputStream {

	public static void main(String[] args) {
		String fileName = "data.dat";
		try {
			DataOutputStream data = new DataOutputStream(new BufferedOutputStream( new FileOutputStream(fileName) ));
			data.writeInt(0);
			System.out.println("共写入了" + data.size() + "个字节");
			data.writeDouble(33.4);
			System.out.println("共写入了" + data.size() + "个字节");
			data.writeChar(23);
			System.out.println("共写入了" + data.size() + "个字节");
			data.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
