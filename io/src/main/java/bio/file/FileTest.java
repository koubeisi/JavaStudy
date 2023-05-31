package bio.file;

import java.io.File;
import java.io.IOException;

/**
 * @author KouBeisi
 */
public class FileTest {

	public static void main(String[] args) {

		/*
		 * File(String pathname) :构造函数创建文件夹 public boolean mkdir() :创建一级路径
		 */
		File file = new File("D:" + File.separator + "File");
		if (!file.exists()) {
			System.out.println("文件不存在，创建中……");
			file.mkdir();
			System.out.println("文件创建成功");
		}

		/*
		 * File(String pathname) :构造函数创建文件夹 public boolean mkdirs() :创建多级路径
		 */
		File file0 = new File("D:" + File.separator + "file" + File.separator + "Java");
		if (!file0.exists()) {
			System.out.println("文件不存在，创建中……");
			file.mkdirs();
			System.out.println("文件创建成功");
		}

		/*
		 * File(String pathname) :构造函数创建文件
		 * public createNewFile() :创建文件
		 */
		File file01 = new File("D:/File/Java2.txt");
		if (!file01.exists()) {
			System.out.println("文件不存在，创建中……");
			try {
				file01.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("文件创建成功");
		}

		/*
		 * File(File parent,String child) :构造函数创建文件
		 *
		 * @parent ：文件夹路径
		 * @child ：文件名称
		 */
		File file1 = new File(file, "Java.txt");
		if (!file1.exists()) {
			System.out.println("文件不存在，创建中……");
			try {
				file1.createNewFile();
				System.out.println("文件创建成功");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		/*
		 * File(String parent,String child) :构造函数创建文件
		 *
		 * @parent ：文件夹路径
		 * @child ：文件名称
		 */
		File file2 = new File("D:/File", "Java1.txt");
		if (!file2.exists()) {

			try {
				System.out.println("文件不存在，创建中……");
				file2.createNewFile();
				System.out.println("文件创建成功");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		/*
		 * File(String parent,String child) :构造函数创建文件夹
		 *
		 * @parent ：文件夹路径
		 * @child ：文件夹名称
		 */
		File file3 = new File("D:/File", "Java");
		if (!file3.exists()) {

			System.out.println("文件不存在，创建中……");
			file3.mkdir();
			System.out.println("文件创建成功");

		}
	}

}
