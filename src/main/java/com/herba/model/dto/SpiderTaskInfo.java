package com.herba.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class SpiderTaskInfo {
    private String name;
    private String startTime;
    private int totalPageCount;
    private int successPageCount;
    private int errorPageCount;
    private String status;
    private int leftPageCount;
    private int pagePerSecond;
    private String errorPages;
    private int thread;

}
