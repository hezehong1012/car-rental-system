package com.org.car.manager.exceptions;

import com.org.car.manager.enums.BusinessExceptionTypeEnum;

public class BusinessException extends RuntimeException{
    public BusinessException(){
        super();
    }

    public BusinessException(BusinessExceptionTypeEnum typeEnum){
        super(typeEnum.getMessage());
    }
}
