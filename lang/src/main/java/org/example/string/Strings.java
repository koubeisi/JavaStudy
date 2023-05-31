package org.example.string;

public class Strings {

	public static void main(String[] args) {

		String str = "Hello world!";
		str= "Hello!";
		test(str);
		//str的值并没有改变
		System.out.println("After Method:" + str);
		
		Dog dog = new Dog("Jack");
		test1(dog);
		//Dog的值并没有改变
		System.out.println("After Method:" + dog);

	}
	
	/**
	 * 方法传递的是引用复制
	 * @param str
	 */
	public static void test(String str) {
		System.out.println("Inner Method1:" + str);
		str = "Hello Java!";
		System.out.println("Inner Method2:" + str);

	}
	
	/**
	 * 方法传递的是引用复制
	 * @param dog
	 */
	public static void test1(Dog dog) {
		System.out.println("Dog Inner Method1:" + dog);
		Dog d = new Dog("Jim");
		dog = d;
		System.out.println("Dog Inner Method2:" + dog);

	}

}

class Dog{
	
	private String name;
	
	public Dog(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Dog [name=" + name + "]";
	}
	
}
