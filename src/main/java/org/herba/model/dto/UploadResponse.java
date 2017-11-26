package org.herba.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class UploadResponse extends SimpleResponse {

    private List<String> LinkApi;

    public UploadResponse(int code, String message) {
        super(code, message);
    }

    public UploadResponse(int code, String message, List<String> linkApi) {
        super(code, message);
        LinkApi = linkApi;
    }
}
