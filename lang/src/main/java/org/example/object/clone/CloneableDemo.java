package org.example.object.clone;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

/**
 * 继承 Object 类的 clone 方法必须实现 Cloneable 接口
 * @author koubs
 * @date 2020/6/3
 **/
public class CloneableDemo {

    public static void main(String[] args) throws CloneNotSupportedException {
        Student student = new Student("苏南", "女", 21, LocalDate.of(1991, Month.SEPTEMBER, 12));

        Student cloneStudent = student.clone();

        System.out.println(student == cloneStudent);
        System.out.println(student.equals(cloneStudent));


        ArrayList<Student> students = new ArrayList<>();
        students.add(student);
        students.add(cloneStudent);

        ArrayList<Student> cloneStudents = (ArrayList<Student>) students.clone();

        System.out.println(students == cloneStudents);
        System.out.println(students.equals(cloneStudents));

    }
}

class Student implements Cloneable {

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

    @Override
    protected Student clone() throws CloneNotSupportedException {
        return (Student) super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        Student student = (Student) obj;
        return  this.name.equals(student.getName()) && age.equals(student.getAge()) && gender.equals(student.getGender()) && birthday.equals(student.getBirthday());
    }
}
