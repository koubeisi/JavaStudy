package xuetang;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestDataIntputStream {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("order.txt");
		if(!file.exists())
			System.out.println("\"order.txt\" doesn't exists,check the disk...");
		DataInputStream in = new DataInputStream( new BufferedInputStream( new FileInputStream(file) ));
		try {
			
			while(true) {
				char c;
				char[] tmp = new char[200];
				int i = 0;
				while( ( c=in.readChar() ) != '\t') {
					tmp[i] = c;
					i++;
				}
				String name = new String(tmp, 0, i);
				float price = in.readFloat();
				in.readChar();
				int num = in.readInt();
				in.readChar();
				System.out.println("商品：" + name + " 价格:" + price + " 数量：" + num);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("输出完毕...");			
		}finally {
			try {
				in.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
		
}
