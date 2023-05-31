package testListSet;

//import java.util.Scanner;

public class Init {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Students students = new Students();
		
		Student student1 = new Student(1,"Tom"); 
		Student student2 = new Student(0,"Jack");
		Student student3 = new Student(2,"Marry");
		Student[] student4 = {new Student(5,"Harry"),new Student(6,"Jim")};
		Student[] student5 = {new Student(3,"Peter"),new Student(4,"Shaller")};
		Student student3m = new Student(3,"Windy");
		
		/*default insert*/
		students.add(student1);
		students.addId(0 , student2);  //插入添加相当于其它后移
		students.add(student3);        //默认插入添加相当于添加到最后面
		
		/*array insert*/
		students.addArray(student4);
		students.printFor();             //for循环的方式输出
		System.out.println("****************************");
		students.addArrayId(3, student5);
		
		/*modify one of the students*/
		students.setStudent(3, student3m);
		students.printForeach();         //for each的方式输出
		
		System.out.println("****************************");
		students.printIterator();
		
		System.out.println("******Test for HashSet******");
		PD teacher = new PD(0,"Simith");
		teacher.add(student1);
		teacher.addArray(student4);
		teacher.addArray(student5);
		teacher.print();
		
		System.out.println("******Test for HashSet******");
		Student s1 = teacher.searchId();
		System.out.println("学生ID：" + s1.getId() + " 学生姓名：" + s1.getName());
		System.out.println("******Test for HashSet******");
//		Scanner in = new Scanner(System.in);
//		in.nextLine();
		Student s2 = teacher.searchName();
		System.out.println("学生ID：" + s2.getId() + " 学生姓名：" + s2.getName());
		/*为什么in.close()函数的使用会抛出异常*/
	}
	
}
