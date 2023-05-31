package com.koubs.reflect.clazz;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author KouBeisi
 * @since 2021/9/7
 */
public class ReflectTest {

    @Test
    void newInstance() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final Class<Airplane> clazz = Airplane.class;

        final Airplane airplane = clazz.newInstance();
        System.out.println(airplane);

        final Constructor<Airplane> constructor = clazz.getDeclaredConstructor();
        final Airplane instance = constructor.newInstance();
        System.out.println(instance);

    }

    @Test
    void method() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final Class<Airplane> clazz = Airplane.class;
        final Constructor<Airplane> constructor = clazz.getDeclaredConstructor();
        final Airplane instance = constructor.newInstance();

        final Method setCompany = clazz.getMethod("setCompany", String.class);
        setCompany.invoke(instance, "吉祥航空");

        final Method getCompany = clazz.getMethod("getCompany");
        final Object invoke = getCompany.invoke(instance);
        System.out.println(invoke);
        System.out.println(instance.getCompany());
    }
}
