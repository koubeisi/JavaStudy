package serializable;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * @author koubs
 * @date 2020/6/3
 **/
public class SerializableDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student s1 = new Student("Tom","boy",12,LocalDate.of(2008, Month.MAY,30));
        Student s2 = new Student("玉皇","男",23323,LocalDate.of(8, Month.MAY,30));
        Student s3 = new Student("王母","女",12000,LocalDate.of(8, Month.MAY,30));
        Student s4 = new Student("金刚","🚺",230,LocalDate.of(1908, Month.MAY,30));
        Student s5 = new Student("蜘蛛侠","🚹",24,LocalDate.of(1994, Month.MAY,30));

        ArrayList<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);

        //序列化
        Path path = Paths.get("students.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(path.toFile());

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(students);

        objectOutputStream.close();
        fileOutputStream.close();

        //反序列化
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path.toFile()));
        List<Student> list = (List<Student>) objectInputStream.readObject();

        for (Student student:list){
            System.out.println(student.toString());
        }

    }




}

class Student implements Serializable {

    private String name;
    private String gender;
    private Integer age;
    private LocalDate birthday;

    public Student(String name,String gender,Integer age,LocalDate birthday){
        this.age = age;
        this.gender = gender;
        this.name=name;
        this.birthday=birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}
