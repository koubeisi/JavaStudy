package bio;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SerializableTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Path path = Paths.get("Demo",File.separator,"obj.date");

//		//1、对象序列化
//		OutputStream os =Files.newOutputStream(path);
//		ObjectOutputStream oos = new ObjectOutputStream(os);
//
//		Student stu1 =new Student("10001","张三",19);
//		oos.writeObject(stu1);
//		oos.flush();
//
//		oos.close();
//
//		//2、对象反序列化
//		InputStream is = Files.newInputStream(path);
//		ObjectInputStream ois=new ObjectInputStream(is);
//
//		Student stu=(Student) ois.readObject();
//		System.out.println(stu.toString());
//
//		ois.close();

		/**********测试序列化中子父类构造函数问题*************/
		//1、对象序列化
		OutputStream os =Files.newOutputStream(path);
		ObjectOutputStream oos = new ObjectOutputStream(os);

		Apple apple = new Apple();
		oos.writeObject(apple);

		oos.flush();
		oos.close();

		//2、对象反序列化
		InputStream is=Files.newInputStream(path);
		ObjectInputStream ois=new ObjectInputStream(is);

		apple =(Apple) ois.readObject();
		System.out.println(apple);
		ois.close();
	}

	static class Food implements Serializable {
		private static final long serialVersionUID = 1L;

		public Food() {
			System.out.println("Fool类的无参构造函数……");
		}
	}
	static class Fruit extends Food {
		private static final long serialVersionUID = 1L;

		public Fruit() {
			System.out.println("Fruit类的无参构造函数……");
		}
	}
	static class Apple extends Fruit {
		private static final long serialVersionUID = 1L;

		public Apple() {
			System.out.println("Apple类的无参构造函数……");
		}
	}
}
