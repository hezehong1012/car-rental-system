package com.org.car.manager;


import com.org.car.manager.model.Car;
import com.org.car.manager.model.UserRentalCar;
import com.org.car.manager.service.RentalCarService;
import com.org.car.manager.service.db.CarRepository;
import com.org.car.manager.service.db.UserRentalCarRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarManagerApplication.class)
public class RentalCarServiceTest {
    private static final Logger log = LoggerFactory.getLogger(RentalCarServiceTest.class);

    @Resource
    private RentalCarService rentalCarService;

    @Resource
    private CarRepository carRepository;

    @Resource
    private UserRentalCarRepository userRentalCarRepository;

    public void setupBefore(String functionName){
        log.info("{} empty car stock :", functionName);
        userRentalCarRepository.deleteAll();
        carRepository.deleteAll();
        for(Car car : InitParams.initCarList){
            carRepository.save(car);
        }
        // fetch all customers
        log.info("car found with findAll():");
        log.info("-------------------------------");
        for (Car car : carRepository.findAll()) {
            log.info(car.toString());
        }
        log.info("------------{} step before-------------------", functionName);
    }

//    @After
//    public void setupAfter(){
//        log.info("empty car stock :");
//        repository.deleteAll();
//    }

    @Test
    public void testGetAllResidueStockCar(){
        this.setupBefore("testGetAllResidueStockCar");
        // STEP 1 库存汽车总数等于初始化汽车总数
        List<Car> carList = rentalCarService.getAllResidueStockCar();
        Assert.assertTrue(InitParams.initCarList.size() == carList.size());

    }

    @Test
    public void testGetAllUserRentalCar(){
        this.setupBefore("testGetAllUserRentalCar");
        List<Car> allCarList = rentalCarService.getAllResidueStockCar();
        List<UserRentalCar> userRentalCarList = rentalCarService.getAllUserRentalCar();
        // STEP 1 没有出借一辆车，所以为空
        Assert.assertTrue(CollectionUtils.isEmpty(userRentalCarList));
        UserRentalCar rentalCar = new UserRentalCar();
        rentalCar.setCarId(allCarList.get(0).getId());
        rentalCar.setUserId("xxx");
        rentalCarService.rendCar(rentalCar);
        // STEP 2 出借一辆车,所以查询结果为1
        List<UserRentalCar> userRentalCarListOne = rentalCarService.getAllUserRentalCar();
        Assert.assertTrue(userRentalCarListOne.size() == 1);
    }

    @Test
    public void testRendCar(){
        this.setupBefore("testRendCar");
        List<Car> allCarList = rentalCarService.getAllResidueStockCar();
        List<UserRentalCar> userRentalCarList = rentalCarService.getAllUserRentalCar();
        // STP1 没有出借一辆车，所以为空
        Assert.assertTrue(CollectionUtils.isEmpty(userRentalCarList));
        UserRentalCar rentalCar = new UserRentalCar();
        rentalCar.setCarId(allCarList.get(0).getId());
        rentalCar.setUserId("xxx");
        rentalCarService.rendCar(rentalCar);
        // STEP2 出借一辆车,所以查询结果为1 并且出借的
        List<UserRentalCar> userRentalCarListOne = rentalCarService.getAllUserRentalCar();
        // STEP3 查询结果为
        Assert.assertTrue(userRentalCarListOne.size() == 1 &&
                userRentalCarListOne.get(0).getCarId().equals(allCarList.get(0).getId()));

    }

    @Test
    public void testReturnCar(){
        this.setupBefore("testReturnCar");

        List<Car> allCarList = rentalCarService.getAllResidueStockCar();
        List<UserRentalCar> userRentalCarList = rentalCarService.getAllUserRentalCar();
        // STEP1 没有出借一辆车，所以为空
        Assert.assertTrue(CollectionUtils.isEmpty(userRentalCarList));
        UserRentalCar rentalCar = new UserRentalCar();
        Long carId = allCarList.get(0).getId();
        rentalCar.setCarId(carId);
        rentalCar.setUserId("xxx");
        rentalCarService.rendCar(rentalCar);
        // STEP2 出借一辆车,所以查询结果为1 并且出借的
        List<UserRentalCar> userRentalCarListOne = rentalCarService.getAllUserRentalCar();
        // STEP3 查询结果为
        Assert.assertTrue(userRentalCarListOne.size() == 1 &&
                userRentalCarListOne.get(0).getCarId().equals(carId));
        // STEP4 归还汽车，查询库存为满
        rentalCarService.returnCar(rentalCar);
        List<Car> residueCarList = rentalCarService.getAllResidueStockCar();
        Assert.assertTrue(residueCarList.size() == InitParams.initCarList.size());

    }
}
