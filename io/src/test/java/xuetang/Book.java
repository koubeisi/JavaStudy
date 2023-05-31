package xuetang;

import java.io.Serializable;

public class Book implements Serializable {
	private static final long serialVersionUID = -7658937756194250508L;
	private String name;
	private String author;
	public Book(String name,String author) {
		this.name = name;
		this.author = author;
	}
	@Override
	public String toString() {
		String s = "Name:" + name + " Author:" + author;
		return s;
	}
	
}
