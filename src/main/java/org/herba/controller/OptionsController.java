package org.herba.controller;

import org.herba.model.entity.Options;
import org.herba.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class OptionsController {
    @Autowired
    OptionService optionService;
    @RequestMapping(value = "/admin/options")
    public List<Options> getAllOptions() {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "后台请求所有选项");
        return  optionService.selectAll();
    }
}
