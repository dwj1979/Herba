package org.herba.model.dto;

import lombok.Data;

@Data
public class UploadResult {
    private int code;
    private  String message;
    private  String LinkApi;

    public UploadResult() {
    }

    public UploadResult(int code, String message, String linkapi) {
        this.code = code;
        this.message = message;
        LinkApi = linkapi;
    }
}
