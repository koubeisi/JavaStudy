package org.example.optional.demo1;

import java.util.Optional;

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

    public Optional<String> getName() {
        return Optional.ofNullable(name) ;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<Car> getCar() {
        return Optional.ofNullable(car);
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
