package org.example.user.pojo;

import lombok.Getter;

@Getter
public enum Error {
    //服务器异常
    SERVER_ERROR(10000),

    //普通异常
    //重名
    DUPLICATE_NAME(1001),
    NOT_FOUND(1002),
    ;
    private Integer code;

    Error(Integer code) {
        this.code = code;
    }
}
