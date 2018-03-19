package com.herba.controller;

import com.herba.model.dto.DataResponse;
import com.herba.model.dto.ResponseCode;
import com.herba.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OptionsController {
    @Autowired
    OptionService optionService;

    /**
     *  getAllOptions   获取所有选项
     *
     * */
    @RequestMapping(value = "/admin/options")
    public DataResponse getAllOptions() {
        return new DataResponse(ResponseCode.SUCCESS.getCode(), "", optionService.selectAll());
    }
}
