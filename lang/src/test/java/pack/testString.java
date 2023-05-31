package pack;

public class testString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*如何把一个字符数组转换为字符串*/
		char[] c = {'I','\'','m',' ','a',' ','n','o','o','b','.'};
		String s = new String(c);
//		s = c.toString();
		System.out.println(s);
		System.out.println(c);
		System.out.println(c.toString()); //为什么是一串地址
		
		/*在StringBuffer类中插入字符*/
		StringBuffer s1 = new StringBuffer();
		s1 = s1.append(" love");
		s1 = s1.append(" Java.");
		s1 = s1.insert(0 , 'I');
		System.out.println(s1);
		System.out.println(s1.length()); //包含空格
		
		/*在StringBuffer类中设置字符*/
		StringBuffer s2 = new StringBuffer("I'm a recruit");
		s2.setCharAt(4, 'A');
		System.out.println(s2);
	}

}
