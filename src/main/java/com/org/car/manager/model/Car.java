package com.org.car.manager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String branch;

    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected Car() {

    }
    public Car(Long id, String branch , String name){
        this.id = id;
        this.branch = branch;
        this.name = name;
    }

    @Override
    public String toString() {
        return "StockCar{" +
                "id=" + id +
                ", branch='" + branch + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
