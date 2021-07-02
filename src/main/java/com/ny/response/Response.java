package com.ny.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yang
 * @version 1.0
 * @date 2021/4/12 15:02
 */
//返回
@AllArgsConstructor
@Data
public class Response<T> implements Serializable {

    //成功码0000
    private static final String successCode= "0000";

    //返回数据
    private T data;

    private String code;

    private String msg;

    public Response(){
        this.code = successCode;
        this.msg = "请求成功！";
    }

}
