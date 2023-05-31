package xuetang;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestFileWrite {

	public static void main(String[] args) {
		File file = new File("Hello.txt");
		if(!file.exists()) {
			try {
				file.createNewFile();
				System.out.println("Hello.txt创建成功！");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileWriter fw = new FileWriter(file);
			fw.write("Hello World!\n");
			fw.write("Game over\n");
			fw.write("!!!!");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
