package com.org.car.manager;


import com.org.car.manager.model.Car;
import com.org.car.manager.service.db.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
@ComponentScan(basePackages = {"com.org.car.manager"})
@EnableAutoConfiguration
public class CarManagerApplication extends SpringBootServletInitializer {
    private static final Logger log = LoggerFactory.getLogger(CarManagerApplication.class);

    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        SpringApplication.run(CarManagerApplication.class, args);
        long endTime = System.currentTimeMillis();
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< CarManagerApplication start success, use: " + Double.valueOf(endTime - startTime) + " >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Bean
    public CommandLineRunner demo(CarRepository repository) {
        return (args) -> {
            // save a few customers
//            repository.save(new Car((long)1, "Toyota Camry", "Toyota Camry_001"));
//            repository.save(new Car((long)2, "Toyota Camry", "Toyota Camry_002"));
//
//            repository.save(new Car((long)3, "BMW 650", "BMW 650_001"));
//
//            repository.save(new Car((long)4, "BMW 650", "BMW 650_002"));

            for(Car car : InitParams.initCarList){
                repository.save(car);
            }

            // fetch all customers
            log.info("car found with findAll():");
            log.info("-------------------------------");
            for (Car car : repository.findAll()) {
                log.info(car.toString());
            }
            log.info("-------------------------------");


        };
    }
}

