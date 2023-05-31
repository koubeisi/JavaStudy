package com.koubs.testMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author KouBeisi
 */
public class Courses {
	private Map<Integer , Course> courses ;

	public Courses() {
		courses = new HashMap<Integer,Course>();
	}

	public void add(Integer id, Course course) {
		if ( courses.containsKey(id) ) {
			System.out.println("You already contain the key.");
		}else {
			courses.put(id, course);
		}
	}

	/*����HashMap�е�ֵ����һ*/
	public void print() {
		System.out.println("�пγ�����" + courses.size());
		for( Integer s : courses.keySet()) {
			System.out.println("�γ�ID��" + s.intValue() + " �γ����֣�" + courses.get(s).getName());
		}
	}

	/*����HashMap�е�ֵ������*/

	/*ɾ��HashMap�е�ֵ*/
	public void remove() {
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.println("������Ҫɾ���γ̵�keyֵ��");
			Integer i = new Integer(in.nextInt());
			if(courses.containsKey(i)) {
				courses.remove(i);
				break;
			}
			else {
				continue;
			}
		}
	}

	/*�޸�HashMap�е�ֵ*/
	public void modify() {
		System.out.println("������Ҫ�޸ĵ�keyֵ��");
		Scanner in = new Scanner(System.in);
		while (true) {
			Integer i = new Integer(in.nextInt());
			in.nextLine();   //nextInt() ��Ҫ��nextLine() ,����س����Ž�����һ���������
			if (courses.get(i) == null) {
				System.out.println("���ID�����ڣ�����������");
				continue;
			}else {
				System.out.println("������Ҫ�޸ĵ�valueֵ��");
				String s = new String(in.nextLine());
				Course c = new Course(i,s);
				courses.put(i, c);
				break;
			}
		}
	}
}
