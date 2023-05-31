package com.koubs.testMap;

/**
 * @author KouBeisi
 */
public class Init {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Course c1 = new Course(1,"Math");
		Course c2 = new Course(2,"History");
		Course c3 = new Course(3,"English");

		Courses courses = new Courses();
		courses.add(Integer.valueOf( c1.getId() ), c1);
		courses.add(Integer.valueOf( c2.getId() ), c2);
		courses.add(Integer.valueOf( c3.getId() ), c3);
		courses.print();

		courses.remove();
		courses.print();

		courses.modify();
		courses.print();
	}

}
