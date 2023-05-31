package org.example.optional.demo2;

/**
 * @author koubs
 * @date 2021/4/4
 */
public class Person {

    private String name;

    private Car car;

    public Person(){}

    public Person(String name, Car car) {
        this.name = name;
        this.car = car;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCar(Car car) {
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public Car getCar() {
        return car;
    }
}
