package org.example.wtp.pojo.req.common;

import lombok.Data;

@Data
public class Result<T> {
    private T data;
    private String msg;
    private Integer code;

    private Result(T data, String msg, Integer code) {
        this.data = data;
        this.msg = msg;
        this.code = code;
    }

    public static <T> Result<T> ok() {
        return new Result<>(null, "success", 200);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(data, "success", 200);
    }


    public static <T> Result<T> fail() {
        return new Result<>(null, "fail", 400);
    }

}
