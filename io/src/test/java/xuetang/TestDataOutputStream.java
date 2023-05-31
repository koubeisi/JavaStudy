package xuetang;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestDataOutputStream {

	public static void main(String[] args) {
		File file = new File("order.txt");
		if(!file.exists()) {
			System.out.println("\"order.txt\" doesn't exist,create...");
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		try {
			System.out.println("The process begans...");
			DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
			String[] name = {"衬衣", "手套", "围巾"};
			float[] price = {98.3f, 30.3f, 50.5f };
			int[] num = {3,2,1};
			for(int i = 0; i < 3; i++) {
				out.writeChars(name[i]);
				out.writeChar('\t');
				out.writeFloat(price[i]);
				out.writeChar('\t');
				out.writeInt(num[i]);
				out.writeChar('\n');
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
