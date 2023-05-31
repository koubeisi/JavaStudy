/*
 * 本实验是将一个输入流传递给一个输出流
 * 用来实验InputStream和OutputStream的用法
 * */
package shiyanlou;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TestStream {
	
	public static void t(InputStream in) {
		int i;
		try {
			i = in.read();
			System.out.println((char)i);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static void p(InputStream in, OutputStream out) {
		try {
//			byte[] i = new byte[100]; 
			int len = in.read() ;
			while ( len != -1) {
				out.write(len);
				len = in.read();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		System.out.println("Please enter a char:");
		TestStream.t(System.in);
		
		System.out.println("Please enter a array:");
		TestStream.p(System.in, System.out);
		
	}

}
