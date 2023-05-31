package testreadwrite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
/*
 * 1.通过标准输入流输入后，再通过输出流BufferedReader显示到屏幕上
 * 2.通过printStream输出到标准输出流中
 */
public class TestReaderAndPrint {

	public static void testBufferedReader() throws IOException {
		InputStreamReader isr = new InputStreamReader( System.in );
		BufferedReader in = new BufferedReader ( isr );
		String s;
		while( (s = in.readLine()) != "bye" ) {
			System.out.println(s);
		}
	}
	
	public static void testPrintStream() throws FileNotFoundException {

		PrintStream out = new PrintStream(System.out, true);
		out.println('a');
	}
	public static void testPrint() throws Exception {
		PrintStream out = null; 
        out = new PrintStream( new FileOutputStream( new File(
        		"G:" + File.separator + "Java" + File.separator + "workspace" + File.separator + "Demo_IO" + File.separator + "test.txt") ) );
        out.print("Hello ");
        out.println("World.");
        String name = "Tom";
        int age = 40;
        float score = 99.5f;
        char sex = 'f'; 
        out.printf("姓名：%s；年龄：%d；成绩：%f；性别：%c",name,age,score,sex); 
        out.close();
	}
	public static void main(String[] args) throws Exception {
//		testBufferedReader();
//		testPrintStream();
		testPrint();
	}

}
