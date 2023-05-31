package bio;

import java.io.Serializable;

public class Student implements Serializable{

	private static final long serialVersionUID = -6464798329732200235L;
	private String id;
	private String name;
	private transient Integer age;

	public Student() {

	}

	public Student(String id, String name, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException{
            // 把JVM默认序列化的元素进行序列化操作
            s.defaultWriteObject();

            // 自己完成age的序列化
            s.writeInt(age);
        }

    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {

            // 把虚拟机默认的元素进行反序列化操作
            s.defaultReadObject();

            // 自己完成age的反序列化操作
            this.age=s.readInt();

    }
}
