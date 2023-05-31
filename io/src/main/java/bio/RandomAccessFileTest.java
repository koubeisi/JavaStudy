package bio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class RandomAccessFileTest {

	public static void main(String[] args) {

		//创建文件夹对象
		File demo = new File("demo");
		if(!demo.exists()) {
			demo.mkdir();
		}
		//创建文件对象
		File file=new File(demo,"raf.dat");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//创建RandomAccessFile对象
		RandomAccessFile raf = null;
		try {
			 raf=new RandomAccessFile(file,"rw");
			 System.out.println(raf.getFilePointer());

			 /*写文件*/
			 //写入一个字节
			 raf.write('A');
			 System.out.println(raf.getFilePointer());
			 //写入一个int，4个字节
			 raf.writeInt(127);
			 System.out.println(raf.getFilePointer());
			 //写入一个char，2个字节
			 raf.writeChar('C');
			 System.out.println(raf.getFilePointer());
			 //写入一个long，8个字节
			 raf.writeLong(4);
			 System.out.println(raf.getFilePointer());

			 /*读文件，必须把指针移到头部*/
			 raf.seek(0);
			 //一次性把文件内容读取到字节数组中
			 byte[] buf=new byte[(int) raf.length()];
			 int i=raf.read(buf);
			 //输出字节内容
			 System.out.println(Arrays.toString(buf));
			 System.out.println(i);
			//输出字节内容
			 String str=new String(buf);
			 System.out.println(str);

			 //关闭
			 raf.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
