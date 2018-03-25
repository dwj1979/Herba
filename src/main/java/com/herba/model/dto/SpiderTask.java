package com.herba.model.dto;

import lombok.Data;

@Data
public class SpiderTask {
    private  String startUrl;
    private  String urlRule;
    private  String titleRule;
    private  String authorRule;
    private  String textRule;

}
