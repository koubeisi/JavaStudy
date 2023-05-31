/**
 * 创建一个书籍对象，并把它输出到一个文件book.dat中，然后再把该对象读出来，在屏幕上显示对象信息
 */
package xuetang;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestSerializable{

	public static void main(String[] args) {
		Book b1 = new Book("Java","Tomcat");
		Book b2 = new Book("MySQL","Socket");
		File file = new File("book.dat");
		ObjectOutputStream out = null;
		try {
			if(!file.exists())
				file.createNewFile();
			out = new ObjectOutputStream( new FileOutputStream(file) );
			out.writeObject(b1);
			out.writeObject(b2);			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		ObjectInputStream in = null; 
		Book book1 = null;
		Book book2 = null;		
		try {
			in = new ObjectInputStream( new FileInputStream(file) );
			book1 = (Book)in.readObject();
			book2 = (Book)in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(book1);
		System.out.println(book2);
	}

}
