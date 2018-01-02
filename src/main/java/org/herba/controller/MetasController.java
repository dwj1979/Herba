package org.herba.controller;

import com.github.pagehelper.PageInfo;
import com.sun.deploy.net.HttpResponse;
import org.herba.model.entity.Metas;
import org.herba.service.MetaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class MetasController {
    @Autowired
    MetaService metaService;

    /**
     * getAllCategorys 获取所有分类
     */
    @RequestMapping(value = {"/admin/categorys"})
    public List<Metas> getAllCategorys(HttpServletResponse httpResponse) {
        List<Metas> allCategorys = metaService.selectAllCategorys();
        return allCategorys;
    }

    /**
     * getCategoryById 根据ID获取元素
     */
    @RequestMapping(value = {"/admin/category/{mid}", "/admin/tag/{mid}"})
    public Metas getCategoryById(@PathVariable int mid) {
        return metaService.selectCategoryBymid(mid);
    }

    /**
     * getAllTags 获取所有标签
     */
    @RequestMapping(value = {"/admin/tags"})
    public List<Metas> getAllTags() {
        List<Metas> allTags = metaService.selectAllTags();
        return allTags;
    }

    /**
     * getCategorysByPage 分页获取分类信息
     */
    @RequestMapping(value = {"/admin/category/page/{pageNo}"})
    public PageInfo getCategorysByPage(@PathVariable int pageNo, @RequestParam int parent) {
        PageInfo pageInfo = new PageInfo();
        pageInfo = metaService.selectCategorysByPageAndParent(pageNo, 5, parent);
        return pageInfo;
    }

    /**
     * saveMeta 保存元素
     */
    @RequestMapping(value = {"/admin/save/meta"})
    public void saveMeta(@RequestBody Metas meta) {
        if (meta.getMid() != null){
            metaService.updateMetas(meta);
        }else{
            metaService.insertSelective(meta);
        }
    }
}