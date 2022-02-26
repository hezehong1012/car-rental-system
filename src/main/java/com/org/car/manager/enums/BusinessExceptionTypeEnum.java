package com.org.car.manager.enums;

public enum BusinessExceptionTypeEnum {
    ILLEGAL_CAR("ILLEGAL_CAR", "当前租赁的汽车不在库存中"),

    RENTED_CAR("RENTED_CAR", "当前租赁的汽车已出租"),

    NOT_RENTED_CAR("NOT_RENTED_CAR", "当前租赁的汽车已尚未出租，无需规划"),
    ;

    private String type;

    private String message;

    BusinessExceptionTypeEnum(String type, String message){
        this.type = type;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
