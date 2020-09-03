package com.sunkang.apiCommon.entity;

import java.io.Serializable;

/**
 * 支付实体
 */
public class Payment implements Serializable {

    private Integer id;

    private String serial;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}
