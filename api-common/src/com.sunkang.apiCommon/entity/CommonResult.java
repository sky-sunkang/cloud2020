package com.sunkang.apiCommon.entity;

/**
 * 公共返回类
 */
public class CommonResult<T> {

    private int code;

    private String mes;

    private T data;

    public CommonResult() {
    }

    public CommonResult(ResponseStatus status, String mes) {
        this.code = status.getCode();
        this.mes = mes;
        this.data = null;
    }

    public CommonResult(ResponseStatus status, String mes, T data) {
        this.code = status.getCode();
        this.mes = mes;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
