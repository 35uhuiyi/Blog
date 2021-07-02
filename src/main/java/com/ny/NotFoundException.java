package com.ny;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //带有@ResponseStatus注解的异常类会被ResponseStatusExceptionResolver 解析。
//可以实现自定义的一些异常,同时在页面上进行显示
public class NotFoundException extends RuntimeException {

    public NotFoundException(){

    }
    public NotFoundException(String message){
        super(message);

    }
    public NotFoundException(String message, Throwable cause){
        super(message, cause);

    }
}
