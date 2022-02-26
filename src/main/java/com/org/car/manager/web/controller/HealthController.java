package com.org.car.manager.web.controller;

import com.org.car.manager.model.ResultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/health")
public class HealthController {

    @GetMapping(value = "/alive")
    public ResultResponse<String> alive(){
        return ResultResponse.success("hello world!");
    }
}
