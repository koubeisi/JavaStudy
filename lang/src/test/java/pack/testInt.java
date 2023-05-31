package pack;

public class testInt {
	public static void main(String[] args) {
		Integer i = new Integer(10);
		Integer j = new Integer("10");
		Integer k = 10;
		System.out.println(i + " " + i.intValue() + " " + i.floatValue()
		 + " " + i.byteValue());
		System.out.println("***************");
		System.out.print(j + " ");
		System.out.println(j.intValue());
		System.out.println("***************");
		System.out.print(k + " ");
		System.out.println(k.intValue());
	}

}
