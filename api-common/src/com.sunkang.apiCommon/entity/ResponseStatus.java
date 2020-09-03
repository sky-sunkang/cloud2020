package com.sunkang.apiCommon.entity;

/**
 * 返回状态
 */
public enum ResponseStatus {
    OK(200,"成功"),ERROR(500,"服务器异常");
    int code;
    String message;

    ResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
