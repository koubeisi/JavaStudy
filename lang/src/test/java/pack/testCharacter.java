package pack;

public class testCharacter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] c = {'A' , '1' , 'a' , '%'};
		for(char e : c)
			System.out.print(e + " ");
		System.out.println("***************");
		for(char e : c) {
			if(Character.isDigit(e))
				System.out.println(e + " is a digit.");
			if(Character.isLetter(e))
				System.out.println(e + " is a letter.");
			if(Character.isLowerCase(e))
				System.out.println(e + " is a lower case.");
			if(Character.isUpperCase(e))
				System.out.println(e + " is a upper case");
			if(Character.isUnicodeIdentifierPart(e))
				System.out.println(e + " is UnincodeIdentifierPart");
		}
		System.out.println("***************");
		char e = ' ';
		if(Character.isWhitespace(e))
			System.out.println(e + " is a white space.");
		if(Character.isSpaceChar(e))
			System.out.println(e + " is a space char.");
	}

}
