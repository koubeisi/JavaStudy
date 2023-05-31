package imooc.encode;


public class Encode {

	public static void main(String[] args) throws Exception {
		
		String s = "慕课ABC";
		
		//把字符串转换为字节数组，用的是项目默认的编码，此处为UTF-8
		byte[] byte1 = s.getBytes(); 
		//把字节（转换成了int）以16进制的方式显示
		System.out.println("项目默认编码：");
		for(byte b: byte1){
			System.out.print(Integer.toHexString(b & 0xff)  + "  ");
		}
		System.out.println();
		
		
		//gbk编码中文占用两个字节，英文占用一个字节
		byte[] byte2 = s.getBytes("gbk");
		System.out.println("GBK编码：");
		for(byte b: byte2){
			System.out.print(Integer.toHexString(b & 0xff)  + "  ");
		}
		System.out.println();
		
		
		//UTF-8编码中，中文占用1个字节，英文占用1个字节
		byte[] byte3 = s.getBytes("utf-8");
		System.out.println("UTF-8编码：");
		for(byte b: byte3){
			System.out.print(Integer.toHexString(b & 0xff)  + "  ");
		}
		System.out.println();
		
		
		//java是双字节编码utf-16be
		//utf-16be中文占用2个字节，英文占用2个字节
		byte[] byte4 = s.getBytes("utf-16be");
		System.out.println("UTF-16be编码：");
		for(byte b: byte4){
			System.out.print(Integer.toHexString(b & 0xff)  + "  ");
		}
		System.out.println();
		
		
		/*
		 * 当你的字节序列是某种编码时，这时候想把字节序列变成字符串
		 * 也需要用这种方式，否则会出现乱码
		 */
		String str1 = new String(byte4);//用项目默认的编码
		System.out.println(str1);
		String str2 = new String(byte4,"UTF-16be");
		System.out.println(str2);
	}

}
