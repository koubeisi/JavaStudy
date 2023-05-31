package org.example.optional.demo1;

import java.util.Optional;

/**
 * @author koubs
 * @date 2021/4/4
 */
public class Car {

    private String brand;

    private Insurance insurance;

    public Car(){}

    public Car(String brand, Insurance insurance) {
        this.brand = brand;
        this.insurance = insurance;
    }

    public Optional<String> getBrand() {
        return Optional.ofNullable(brand);
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Optional<Insurance> getInsurance() {
        return Optional.ofNullable(insurance) ;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }
}
