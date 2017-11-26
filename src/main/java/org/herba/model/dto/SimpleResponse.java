package org.herba.model.dto;

import lombok.Data;

@Data
public class SimpleResponse {
    //    code 为0为异常
    private int code;
    private String message;

    public SimpleResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
