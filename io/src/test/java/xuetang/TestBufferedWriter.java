package xuetang;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestBufferedWriter {

	public static void main(String[] args) {
		File file = new File("newHello.txt");
		if(!file.exists()) {
			try {
				file.createNewFile();
				System.out.println("newHello.txt创建成功！");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("Hello World!");
			bw.newLine();
			bw.write("Game over");
			bw.newLine();
			bw.write("WIN!!!!");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
