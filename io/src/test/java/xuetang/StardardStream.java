package xuetang;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/*
 * 标准输入/输出重新定向
 * 实现对文件的复制
 */

public class StardardStream {

	public static void main(String[] args) throws IOException {
		//配置输入流指向text.tx
		BufferedInputStream in = new BufferedInputStream(new FileInputStream("text.txt"));
		//配置输出流指向text2.txt
		PrintStream out = new PrintStream(new FileOutputStream("text2.txt"));
		//重新定向标准输入/输出流
		System.setIn(in);
		System.setOut(out);
		System.setErr(out);
		//此时System.in指向text.txt,因此输入就从该文件输入
	    //System.out指向text2.txt，因此就输出到了改文件
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s ;
		while( ( s = br.readLine() ) != null ) {
			System.out.println(s);
		}
		
		
	}

}
