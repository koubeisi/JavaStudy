package org.example.object;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

/**
 * @author koubs
 * @date 2020/6/3
 **/
public class HashAndEqualsDemo {

    public static void main(String[] args) throws CloneNotSupportedException {
        Student student = new Student("Jeck","male",34,LocalDate.of(1993, Month.MAY,23));
        System.out.println(student.hashCode());

        Student s1 = (Student) student.clone();
        System.out.println(s1.hashCode());

        System.out.println(student == s1);
        System.out.println(student.equals(s1));
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(name, student.name) &&
                Objects.equals(gender, student.gender) &&
                Objects.equals(age, student.age) &&
                Objects.equals(birthday, student.birthday);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, age, birthday);
    }
}