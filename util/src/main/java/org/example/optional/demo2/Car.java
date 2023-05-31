package org.example.optional.demo2;

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
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public String getBrand() {
        return brand;
    }

    public Insurance getInsurance() {
        return insurance;
    }
}
