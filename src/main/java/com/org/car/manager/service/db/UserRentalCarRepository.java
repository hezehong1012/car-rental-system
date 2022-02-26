package com.org.car.manager.service.db;

import com.org.car.manager.model.UserRentalCar;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRentalCarRepository extends JpaRepository<UserRentalCar, Long> {

    UserRentalCar findByCarId(Long carId);

    void deleteByCarId(Long carId);
}
