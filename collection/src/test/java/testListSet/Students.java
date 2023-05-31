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

	/*ͨ����������ķ�ʽ���*/
	public void add(Student stu) {
		students.add(stu);
	}
	
	/*ͨ����������ķ�ʽ���뵽ָ��λ��*/
	public void addId(int index, Student stu) {
		students.add(index, stu);
	}
	
	/*ͨ������ķ�ʽ���*/
	public void addArray(Student[] stu) {
		List<Student> st = Arrays.asList(stu);
		students.addAll(st);
	}
	
	/*ͨ������ķ�ʽ���뵽ָ��λ��*/
	public void addArrayId(int index, Student[] stu) {
		List<Student> st = Arrays.asList(stu);
		students.addAll(index, st);
	}
	
	/*modify the element*/
	public void setStudent(int index , Student stu) {
		students.set(index, stu);
	}
	
	/*forѭ����ʽ���*/
	public void printFor() {
		Student tmp;
		for(int i = 0; i < students.size() ; i++) {
			tmp = students.get(i);
			System.out.println("ѧ��ID: " + tmp.getId() + "  " +  "ѧ�������� " + tmp.getName());
		}
	}
	
	/*for each��ʽ���*/
	public void printForeach() {
		for(Student tmp : students) {
			System.out.println("ѧ��ID: " + tmp.getId() + "  " +  "ѧ�������� " + tmp.getName());	
		}
	}
	
	/*��������ʽ���*/
	/*��ʵfor eachѭ�����ǵ������ļ�дģʽ*/
	public void printIterator() {
		Iterator<Student> iterator = students.iterator();		
		while (iterator.hasNext()) {
			Student stu = iterator.next(); 
			System.out.println("ѧ��ID: " + stu.getId() + "  " +  "ѧ�������� " + stu.getName());
		}
		/*ÿ����һ��next()���͵���һ�Σ�����Ҫ��ǰnext()����ֻ�ܵ���һ�Σ������������ַ����ͻ��׳�Խ���쳣
		 * System.out.println("ѧ��ID: " + iterator.next().getId() + "  " +  "ѧ�������� " + iterator.next().getName());
		*/
	}
}
