package xuetang;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Person implements Externalizable {
	private String name;
	private int age;
	//缺省构造器必须建立，否则就会出错为 no valid constructor
	public Person() {
		System.out.println("Defaut constructor.");
	}
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.name = (String)in.readObject();
		this.age = in.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(name);
		out.writeInt(age);
	}
	@Override
	public String toString() {
		return "Name:" + name + " Age:" + age;
	}
}
