package org.example.user.pojo;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
public class ResponseData<T> {

    private T data;
    private String message;
    private Boolean success;
    private Integer status;
    private String errorData;

    private ResponseData(T data, Boolean success, Integer status, String message) {
        this.data = data;
        this.success = success;
        this.status = status;
        this.message = message;
    }

    public ResponseData(T data, Boolean success, Integer status, String message, String errorData) {
        this.data = data;
        this.success = success;
        this.status = status;
        this.message = message;
        this.errorData = errorData;
    }

    public static <T> ResponseData<T> data(T data) {
        return new ResponseData<>(data, true, HttpStatus.OK.value(), null);
    }

    public static <T> ResponseData<T> success() {
        return new ResponseData<>(null, true, HttpStatus.OK.value(), null);
    }

    public static <T> ResponseData<T> error() {
        return new ResponseData<>(null, false, HttpStatus.BAD_REQUEST.value(), null);
    }

    public static <T> ResponseData<T> error(Error error) {
        return new ResponseData<>(null, false, error.getCode(), error.name());
    }

    public static <T> ResponseData<T> error(Error error, Object errorData) {
        return new ResponseData<>(null, false, error.getCode(), error.name(), JSONObject.toJSONString(errorData));
    }

    public static <T> ResponseData<T> error(T data, Error error) {
        return new ResponseData<>(data, false, error.getCode(), error.name());
    }

    public static <T> ResponseData<T> error(Error error, String msg) {
        return new ResponseData<>(null, false, error.getCode(), msg);
    }

    public static <T> ResponseData<T> error(int code, String msg) {
        return new ResponseData<>(null, false, code, msg);
    }

    public static <T> ResponseData<T> error(int code, String msg, Object errorData) {
        return new ResponseData<>(null, false, code, msg, errorData instanceof String ? String.valueOf(errorData) : JSONObject.toJSONString(errorData));
    }
}
