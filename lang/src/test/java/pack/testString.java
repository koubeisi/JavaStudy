package pack;

public class testString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*��ΰ�һ���ַ�����ת��Ϊ�ַ���*/
		char[] c = {'I','\'','m',' ','a',' ','n','o','o','b','.'};
		String s = new String(c);
//		s = c.toString();
		System.out.println(s);
		System.out.println(c);
		System.out.println(c.toString()); //Ϊʲô��һ����ַ
		
		/*��StringBuffer���в����ַ�*/
		StringBuffer s1 = new StringBuffer();
		s1 = s1.append(" love");
		s1 = s1.append(" Java.");
		s1 = s1.insert(0 , 'I');
		System.out.println(s1);
		System.out.println(s1.length()); //�����ո�
		
		/*��StringBuffer���������ַ�*/
		StringBuffer s2 = new StringBuffer("I'm a recruit");
		s2.setCharAt(4, 'A');
		System.out.println(s2);
	}

}
