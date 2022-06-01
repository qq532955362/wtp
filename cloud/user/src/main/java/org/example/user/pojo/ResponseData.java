package org.example.user.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
public class ResponseData<T> {

    private T data;
    private String message;
    private Integer status;

    private ResponseData(T data, Integer status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public static <T> ResponseData<T> data(T data) {
        return new ResponseData<>(data, HttpStatus.OK.value(), null);
    }

    public static <T> ResponseData<T> success() {
        return new ResponseData<>(null, HttpStatus.OK.value(), null);
    }

    public static <T> ResponseData<T> error() {
        return new ResponseData<>(null, HttpStatus.BAD_REQUEST.value(), null);
    }

    public static <T> ResponseData<T> error(Error error) {
        return new ResponseData<>(null, error.getCode(), error.name());
    }


    public static <T> ResponseData<T> error(T data, Error error) {
        return new ResponseData<>(data, error.getCode(), error.name());
    }

    public static <T> ResponseData<T> error(Error error, String msg) {
        return new ResponseData<>(null, error.getCode(), msg);
    }

    public static <T> ResponseData<T> error(int code, String msg) {
        return new ResponseData<>(null, code, msg);
    }
}
