package testListSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Students {
	private List<Student> students = new ArrayList<Student>();
		
	public Students(Student stu) {
		students.add(stu);
	}
	public Students() {};

	/*通过单个对象的方式添加*/
	public void add(Student stu) {
		students.add(stu);
	}
	
	/*通过单个对象的方式插入到指定位置*/
	public void addId(int index, Student stu) {
		students.add(index, stu);
	}
	
	/*通过数组的方式添加*/
	public void addArray(Student[] stu) {
		List<Student> st = Arrays.asList(stu);
		students.addAll(st);
	}
	
	/*通过数组的方式插入到指定位置*/
	public void addArrayId(int index, Student[] stu) {
		List<Student> st = Arrays.asList(stu);
		students.addAll(index, st);
	}
	
	/*modify the element*/
	public void setStudent(int index , Student stu) {
		students.set(index, stu);
	}
	
	/*for循环方式输出*/
	public void printFor() {
		Student tmp;
		for(int i = 0; i < students.size() ; i++) {
			tmp = students.get(i);
			System.out.println("学生ID: " + tmp.getId() + "  " +  "学生姓名： " + tmp.getName());
		}
	}
	
	/*for each方式输出*/
	public void printForeach() {
		for(Student tmp : students) {
			System.out.println("学生ID: " + tmp.getId() + "  " +  "学生姓名： " + tmp.getName());	
		}
	}
	
	/*迭代器方式输出*/
	/*其实for each循环就是迭代器的简写模式*/
	public void printIterator() {
		Iterator<Student> iterator = students.iterator();		
		while (iterator.hasNext()) {
			Student stu = iterator.next(); 
			System.out.println("学生ID: " + stu.getId() + "  " +  "学生姓名： " + stu.getName());
		}
		/*每调用一次next()，就迭代一次，所有要提前next()方法只能调用一次，所以下面这种方法就会抛出越界异常
		 * System.out.println("学生ID: " + iterator.next().getId() + "  " +  "学生姓名： " + iterator.next().getName());
		*/
	}
}
