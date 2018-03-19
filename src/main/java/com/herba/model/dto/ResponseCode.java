package com.herba.model.dto;

import lombok.Data;

public enum ResponseCode {

    SUCCESS(0), FAIL(1);
    private int code;

    ResponseCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
