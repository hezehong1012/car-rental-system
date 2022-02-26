package com.org.car.manager.web.controller;

import com.org.car.manager.exceptions.BusinessException;
import com.org.car.manager.model.ResultResponse;
import com.org.car.manager.model.UserRentalCar;
import com.org.car.manager.service.RentalCarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@Api(tags = "car rental manager")
@RestController
@RequestMapping(value = "/rental")
public class RentalCarController {
    @Autowired
    private RentalCarService rentalCarService;

    /**
     * @return
     *  all residue stock car list, all car had bend rend if empty
     *  所有可以出借的Car列表，空则代表无汽车可以出借
     * [
     *         {
     *             "id": 1,
     *             "branch": "Toyota Camry",
     *             "name": "Toyota Camry_001"
     *         },
     *         {
     *             "id": 2,
     *             "branch": "Toyota Camry",
     *             "name": "Toyota Camry_002"
     *         },
     *         {
     *             "id": 3,
     *             "branch": "BMW 650",
     *             "name": "BMW 650_001"
     *         },
     *         {
     *             "id": 4,
     *             "branch": "BMW 650",
     *             "name": "BMW 650_002"
     *         }
     *     ]
     */
    @ApiOperation("get all residue stock car list, all car had bend rend if empty")
    @GetMapping(value = "/allResidueStockCar")
    public ResultResponse getAllResidueStockCar(){
        try {
          return ResultResponse.success(rentalCarService.getAllResidueStockCar());
        }catch (BusinessException e){
            return ResultResponse.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        }
    }

    /**
     * @return
     *  all rend car list, not one car had bend rend if empty
     *  所有已出借的Car列表，空则代表无汽车被出借
     * [
     *         {
     *             "id": 1,
     *             "userId": "XX1",
     *             "carId": 1
     *         },
     *         {
     *             "id": 2,
     *             "userId": "XX2",
     *             "carId": 2
     *         },
     *     ]
     */
    @GetMapping(value = "/allUserRentalCar")
    @ApiOperation("get all rend car list, not one car had bend rend if empty")
    public ResultResponse getAllUserRentalCar(){
        try {
            return ResultResponse.success(rentalCarService.getAllUserRentalCar());
        }catch (BusinessException e){
            return ResultResponse.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        }
    }

    /**
     *  rend car
     * @param rendCar
     * {
     *       "id": 5,
     *       "userId": "hezehong015",
     *       "carId": 1
     * }
     * @return
     */
    @PostMapping(value = "/rend")
    @ApiOperation("rend car")
    public ResultResponse rentalCar(@RequestBody UserRentalCar rendCar){
        try{
            rentalCarService.rendCar(rendCar);
            return ResultResponse.success();
        }catch (BusinessException e){
            return ResultResponse.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        }
    }

    /**
     * return car
     * @param returnCar
     * {
     *
     *       "userId": "hezehong015",
     *       "carId": 1
     * }
     * @return
     */
    @DeleteMapping(value = "/return")
    @ApiOperation("return car")
    public ResultResponse returnCar(@RequestBody UserRentalCar returnCar){
        try{
            rentalCarService.returnCar(returnCar);
            return ResultResponse.success();
        }catch (BusinessException e){
            return ResultResponse.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        }
    }
}
