package com.org.car.manager.model;

import org.slf4j.MDC;
import org.springframework.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultResponse<T> {
    /**
     *  状态 200 500 400 401 等
     */
    private Integer status;

    /**
     *  请求requestId
     */
    private String requestId;

    /**
     *  访问时间
     */
    private String date;

    /**
     *  返回信息
     */
    private String message;

    private T result;

    public Integer getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getRequestId() {
        return requestId;
    }

    public T getResult() {
        return result;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ResultResponse(){
        this.date = getCurrentDate();
        this.status = HttpStatus.OK.value();
        this.message = "success";
        this.requestId = MDC.get("requestId");
    }

    public ResultResponse(Integer status, String message, T result){
        this.status = status;
        this.message = message;
        this.result = result;
        this.requestId = MDC.get("requestId");
    }

    private String getCurrentDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    public static <T> ResultResponse success(String message, T result){
        return new ResultResponse(HttpStatus.OK.value(), message,result);
    }

    public static <T> ResultResponse success(T result){
        return success("success", result);
    }

    public static <T> ResultResponse success(){
        return success("success", null);
    }

    public static <T> ResultResponse failure(Integer status, String message, T result){
        return new ResultResponse(status, message, result);
    }
}
