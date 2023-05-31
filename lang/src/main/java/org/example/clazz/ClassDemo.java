package org.example.clazz;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author koubs
 * @date 2020/6/3
 **/
public class ClassDemo {

    private static User user = new User(1,"Tom","female");

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {



        //根据类获取到类的类
        Class<User> clazz = User.class;

        System.out.println(clazz.getName());
        System.out.println(clazz.getSimpleName());

        //根据属性名木获取属性，如果属性是私有的使用getDeclaredField,如果是公共的类使用getField
        Field idField = clazz.getDeclaredField("id");
//        Field id = clazz.getField("id");
        System.out.println(idField.getType());
        System.out.println(idField.getName());
        System.out.println(idField.isAccessible());
//        System.out.println(idField.get(user));

        Field countryField = clazz.getDeclaredField("COUNTRY");
        System.out.println(countryField.get(null));

        //根据方法名获取方法
        Method getIdMethod = clazz.getMethod("getId");
        System.out.println(getIdMethod.getName());
        Class<Integer> returnType = (Class<Integer>) getIdMethod.getReturnType();

//        returnType invoke = getId.invoke(user, 12);

    }

}

class User {

    private static String COUNTRY = "China";

    private Integer id;
    private String name;
    private String gender;

    public User(Integer id, String name, String gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
