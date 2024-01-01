package com.fxz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    public Integer code;  // 响应码，1代表成功，0代表失败
    public String msg;  // 响应消息
    public Object data;  // 返回的数据

    // 成功响应
    public static Result success() {
        return new Result(1,"success",null);
    }

    // 成功响应并返回数据
    public static Result success(Object data) {
        return new Result(1,"success",data);
    }

    public static Result error(String msg) {
        return new Result(0,msg,null);
    }

}
