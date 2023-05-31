package xuetang;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestExtenaliazable {

	public static void main(String[] args) {
		Person p1 = new Person("Java",15);
		Person p2 = new Person("MySQL",10);
		File file = new File("person.dat");
		ObjectOutputStream out = null;
		try {
			if(!file.exists())
				file.createNewFile();
			out = new ObjectOutputStream( new FileOutputStream(file) );
			out.writeObject(p1);
			out.writeObject(p2);			
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
		Person person1 = null;
		Person person2 = null;		
		try {
			in = new ObjectInputStream( new FileInputStream(file) );
			person1 = (Person)in.readObject();
			person2 = (Person)in.readObject();
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
		System.out.println(person1);
		System.out.println(person2);
	}
}
