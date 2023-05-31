import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Max
 * @version 1.0
 * @date 2019/12/30 22:01
 **/
public class LambdaDemo {

    public static void main(String[] args) {

        List<Student> students = new ArrayList<Student>(){
            {
                add(new Student("唐僧",34));
                add(new Student("观音",1244));
                add(new Student("孙悟空",750));
                add(new Student("猪八戒",950));
                add(new Student("白龙马",350));
            }
        };

        Comparator<Student> comparator = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Integer.compare(o1.getAge(),o2.getAge());
            }
        };

        students.sort(comparator);
        for (Student student : students){
            System.out.println(student);
        }
    }
}

class Student{
    private String name;
    private Integer age;

    public Student(String name,Integer age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
