import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class TestArrayList {

	public static void main(String[] args) {
		String[] strs = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
		ArrayList<String> strArr = new ArrayList<String>(Arrays.asList(strs));
		
		for(String s : strArr)
			System.out.print(s + "  ");
		
		Iterator<String> strIter = strArr.iterator();
		String s = null;
		while(strIter.hasNext()) {
			s = strIter.next();
			System.out.println(s);
			if(s.length() > 4)
				strIter.remove();
		}
		
		for(String str : strArr)
			System.out.print(str + "  ");
	}

}
