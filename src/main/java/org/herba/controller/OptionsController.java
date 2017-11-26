package org.herba.controller;

import org.herba.model.entity.Options;
import org.herba.service.OptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class OptionsController {
    @Autowired
    OptionService optionService;


    @RequestMapping(value = "/admin/options")
    public List<Options> getAllOptions() {
        return optionService.selectAll();
    }
}
