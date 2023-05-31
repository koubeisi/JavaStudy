package xuetang;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestBufferedReader {

	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("text.txt");
			BufferedReader br = new BufferedReader(fr);
			String s;
			while( (s = br.readLine()) != null)
				System.out.println(s);
			/*
			 * 使用read()方法，读一个字符写一个字符，当读完时，read()方法会返回-1
			 */
//			int c;
//			while( (c = br.read()) > 0 )
//				System.out.print((char)c);
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
