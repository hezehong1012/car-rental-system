package com.org.car.manager.service;

import com.org.car.manager.model.Car;
import com.org.car.manager.model.UserRentalCar;

import java.util.List;

public interface RentalCarService {
    /**
     *
     * @return 所有可以租借的Car 列表
     */
    List<Car> getAllResidueStockCar();

    List<UserRentalCar> getAllUserRentalCar();

    void rendCar(UserRentalCar userRentalCar);

    void returnCar(UserRentalCar userRentalCar);
}
