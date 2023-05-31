package pack;

public class TestClass {
	
	
	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		String s = new String();
		Class c = s.getClass();
		System.out.println(c.getName());
		c = String.class;
		System.out.println(c.getName());
		c = Class.forName("java.lang.Math");
		System.out.println(c.getName());
		c = c.getSuperclass();
		System.out.println(c.getName());
	}

}
