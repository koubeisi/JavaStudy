package testreadwrite;

/*
 * 该程序显示的是以字节流流转换为字符流方式
 * 从文件中读出数据后输出到控制台
 * */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class TestRead {

	public static void main(String[] args) {
	
		File file = new File("libai.txt");
		
		if (file.exists()) {
			System.out.println("libai.txt exits.");
			
			try {
				
				//文件的输入流是一个字节流
				FileInputStream fis = new FileInputStream(file);
				
				//InputStreamReader是一个字符流，在把字节向字符转换的时候就必须制定文件的编码
				InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
				
				BufferedReader br = new BufferedReader(isr);				
				
				String line;
				while(  (line = br.readLine()) != null )
					System.out.println(line);
				
				br.close();
				isr.close();
				fis.close();
				
				
			} catch (FileNotFoundException e) {
				System.out.println("找不到文件");
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}

}
