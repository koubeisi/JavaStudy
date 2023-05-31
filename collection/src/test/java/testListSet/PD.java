package testListSet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class PD {
	String name;
	int id;
	Set<Student> students;
	
	public PD(int id, String name) {
		this.id = id;
		this.name = name;
		students = new HashSet<Student>();
	}
	
	/*add student one by one*/
	public void add(Student stu) {
		students.add(stu);
	}
	
	/*add a array of students*/
	public void addArray(Student[] stu) {
		students.addAll(Arrays.asList(stu));
	}
	
	/*Search a student's id*/
	public Student searchId() {
		Scanner in = new Scanner(System.in);
		System.out.println("请输入学生姓名：");
		int i = in.nextInt();
		Student s;
		String name;
		for(Student stu : students) {
			if (stu.getId() == i) {
				name = new String(stu.getName());
				s = new Student(i,name);
//				in.close();
				return s;
			}
		}	
//		in.close();
		return new Student(-1,"None");
	}
	
	/*Search a student's name*/
	public Student searchName() {
		Scanner in = new Scanner(System.in);
		System.out.println("请输入学生姓名：");
		String name = in.next();
		Student s;
		int id;
		for(Student stu : students) {
			if (stu.getName().equals(name)) {
				id = stu.getId();
				s = new Student(id,name);
//				in.close();
				return s;
			}
		}	
//		in.close();
		return new Student(-1,"None");
	}
	
	public void print() {
		System.out.println("带队老师ID：" + id + "  姓名：" + name);
		for(Student stu : students) {
			System.out.println("学生ID：" + stu.getId() + " 姓名：" + stu.getName());
		}
	}
}
