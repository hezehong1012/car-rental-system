package com.org.car.manager;

import com.org.car.manager.model.Car;

import java.util.LinkedList;
import java.util.List;

public class InitParams {
    public static final List<Car> initCarList;

    static {
        initCarList = new LinkedList<Car>(){{
            add(new Car((long)1, "Toyota Camry", "Toyota Camry_001"));
            add(new Car((long)2, "Toyota Camry", "Toyota Camry_002"));
            add(new Car((long)3, "BMW 650", "BMW 650_001"));
            add(new Car((long)4, "BMW 650", "BMW 650_002"));
        }};
    }
}
