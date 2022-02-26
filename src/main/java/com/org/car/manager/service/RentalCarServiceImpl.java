package com.org.car.manager.service;

import com.org.car.manager.enums.BusinessExceptionTypeEnum;
import com.org.car.manager.exceptions.BusinessException;
import com.org.car.manager.model.Car;
import com.org.car.manager.model.UserRentalCar;
import com.org.car.manager.service.db.CarRepository;
import com.org.car.manager.service.db.UserRentalCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class RentalCarServiceImpl implements RentalCarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRentalCarRepository userRentalCarRepository;

    /**
     *
     * @return 所有可以租借的Car列表
     */
    @Override
    public List<Car> getAllResidueStockCar(){
        List<Car> allCar = carRepository.findAll();
        List<UserRentalCar> userRentalCars = userRentalCarRepository.findAll();
        List<Long> allRentalCarIds = Optional.ofNullable(userRentalCars).orElse(new LinkedList<>())
                .stream()
                .map(item->{return item.getCarId();}).collect(Collectors.toList());
        return allCar.stream().filter(item->{return !allRentalCarIds.contains(item.getId());}).collect(Collectors.toList());
    }

    @Override
    public List<UserRentalCar> getAllUserRentalCar(){
        return Optional.ofNullable(userRentalCarRepository.findAll()).orElse(new LinkedList<>());
    }

    /**
     *
     * @param userRentalCar
     * @return
     */
    @Override
    public void rendCar(UserRentalCar userRentalCar){
        this.checkIsRendCar(userRentalCar);
        userRentalCarRepository.save(userRentalCar);
    }

    @Override
    public void returnCar(UserRentalCar userRentalCar){
        this.checkIsReturnCar(userRentalCar);
        userRentalCarRepository.deleteById(userRentalCar.getId());
    }

    private void checkIsRendCar(UserRentalCar userRentalCar){
        Optional<Car> car = carRepository.findById(userRentalCar.getCarId());
        //判断当前汽车是否合法
        if(!car.isPresent()){
            throw new BusinessException(BusinessExceptionTypeEnum.ILLEGAL_CAR);
        }

        UserRentalCar tempUserRentalCal = userRentalCarRepository.findByCarId(userRentalCar.getCarId());
        // 判断当前汽车是否已经出租
        if(tempUserRentalCal != null){
            throw new BusinessException(BusinessExceptionTypeEnum.RENTED_CAR);
        }

    }

    private void checkIsReturnCar(UserRentalCar userRentalCar){
        Optional<Car> car = carRepository.findById(userRentalCar.getCarId());
        //判断当前汽车是否合法
        if(!car.isPresent()){
            throw new BusinessException(BusinessExceptionTypeEnum.ILLEGAL_CAR);
        }
        UserRentalCar tempUserRentalCal = userRentalCarRepository.findByCarId(userRentalCar.getCarId());
        //判断是否重复归还
        if(tempUserRentalCal == null){
            throw new BusinessException(BusinessExceptionTypeEnum.NOT_RENTED_CAR);
        }
        userRentalCar.setId(tempUserRentalCal.getId());
    }

}
