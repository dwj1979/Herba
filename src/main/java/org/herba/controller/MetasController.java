package org.herba.controller;

import org.herba.model.entity.Metas;
import org.herba.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@CrossOrigin
@RestController
public class MetasController {
    @Autowired
    MetaService metaService;

    //    获取所有的分类
    @RequestMapping(value = "/admin/categorys")
    public List<Metas> getAllCategorys() {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "后台请求所有文章分类");
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
}
