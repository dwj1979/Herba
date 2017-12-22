package org.herba.controller;

import org.herba.model.entity.Metas;
import org.herba.service.MetaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class MetasController {
    @Autowired
    MetaService metaService;


    //    获取分类
    @RequestMapping(value = {"/admin/categorys"})
    public List<Metas> getAllCategorys() {
        List<Metas> allMetas = metaService.selectAll();
        List<Metas> allCategorys = new ArrayList<>();
        for (Metas meta : allMetas
                ) {
            if (meta.getType().equals("category")) {
                allCategorys.add(meta);
            }
        }
        return allCategorys;
    }

    //    获取标签
    @RequestMapping(value = {"/admin/tags"})
    public List<Metas> getAllTags() {
        List<Metas> allMetas = metaService.selectAll();
        List<Metas> allTags = new ArrayList<>();
        for (Metas meta : allMetas
                ) {
            if (meta.getType().equals("tag")) {
                allTags.add(meta);
            }
        }
        return allTags;
    }
}