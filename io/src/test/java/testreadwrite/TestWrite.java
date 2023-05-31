package testreadwrite;

/*
 * 该方法是将字节流流转换为字符流的方式写入文件中
 * */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class TestWrite {

	public static void main(String[] args) {
		
		File file = new File("text.txt");
		
		try {
			//此时文件会自动创建
			//这种方法的文件写入是具有覆盖性的
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter ost = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter bw = new BufferedWriter(ost);
			
			bw.write("静夜思\n");
			bw.write("床前明月光，\n");
			bw.write("疑是地上霜。\n");
			bw.write("举头望明月，\n");
			bw.write("低头思故乡。");
			
			bw.close();
			ost.close();
			fos.close();
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
