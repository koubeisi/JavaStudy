package xuetang;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Copy {
	private String sourceName,destName;
	private BufferedReader source;
	private BufferedWriter dest;
	private String line;
	
	private boolean openFiles() {
		try {
			source = new BufferedReader(new FileReader(sourceName));
		}catch(IOException e) {
			e.printStackTrace();
			return false;
		}
		try {
			dest = new BufferedWriter(new FileWriter(destName));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	private boolean copyFiles() {		
		try {
			while( ( line = source.readLine() ) != null ) {
				dest.write(line);
				dest.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}	
	private boolean closeFiles() {
		boolean retVal = true;
		try {
			source.close();
			dest.close();
		} catch (IOException e) {
			e.printStackTrace();
			retVal = false;
		}
		return retVal;
	}
	public boolean copy(String src, String dst) {
		sourceName = src;
		destName = dst;
		return openFiles()&&copyFiles()&&closeFiles();
	} 
	
	public static void main(String[] args) {
		if(args.length == 2) {
			if(new Copy().copy(args[0], args[1]))
				System.out.println("Copy is already.");
		}
		else System.out.println("Please Enter File names");

	}

}
