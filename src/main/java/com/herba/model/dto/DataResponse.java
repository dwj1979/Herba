package com.herba.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DataResponse {
    private Object data;
    private int code;
    private String message;
    private Date date;

    public DataResponse(int code, String message, Object data) {
        this.data = data;
        this.code = code;
        this.message = message;
        this.date = new Date();
    }

    public DataResponse() {
        this.date = new Date();
    }
}
