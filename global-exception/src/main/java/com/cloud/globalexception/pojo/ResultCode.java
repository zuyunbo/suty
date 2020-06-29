package com.cloud.globalexception.pojo;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(200, "操作成功"),

    FAILED(500, "响应失败"),

    VALIDATE_FAILED(401, "未授权"),

    ERROR(5000, "未知错误");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}