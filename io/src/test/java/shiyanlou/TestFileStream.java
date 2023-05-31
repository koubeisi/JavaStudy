package shiyanlou;
/*
 * 该程序用来测试FileInputStream和FileOutputStream的用法
 * 来实现对文件的复制
 * */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestFileStream {

	public static void fis(String name) {
		try {
			FileInputStream fis = new FileInputStream(name);
			FileOutputStream fos = new FileOutputStream("text1.txt");
			int b = fis.read();;
			while(b != -1) {
				fos.write(b);
				b = fis.read();
			}
			fos.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {

		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestFileStream.fis("text.txt");
	}

}
