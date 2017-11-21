package org.herba.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class UploadResult {
    private int code;
    private  String message;
    private List<String> LinkApi;

    public UploadResult() {
    }

    public UploadResult(int code, String message, List<String> linkapi) {
        this.code = code;
        this.message = message;
        LinkApi = linkapi;
    }
}
