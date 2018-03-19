package com.herba.controller;

import com.github.pagehelper.PageInfo;
import com.herba.model.dto.DataResponse;
import com.herba.model.dto.ResponseCode;
import com.herba.model.entity.Metas;
import com.herba.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class MetasController {
    @Autowired
    MetaService metaService;

    /**
     * getAllCategorys 获取所有分类
     */
    @RequestMapping(value = {"/admin/categorys"})
    public DataResponse getAllCategorys() {
        return new DataResponse(ResponseCode.SUCCESS.getCode(), "", metaService.selectAllCategorys());
    }

    /**
     * getCategoryById 根据ID获取元素
     */
    @RequestMapping(value = {"/admin/category/{mid}", "/admin/tag/{mid}"})
    public DataResponse getCategoryById(@PathVariable int mid) {
        return new DataResponse(ResponseCode.SUCCESS.getCode(), "", metaService.selectCategoryBymid(mid));
    }

    /**
     * getAllTags 获取所有标签
     */
    @RequestMapping(value = {"/admin/tags"})
    public DataResponse getAllTags() {
        return new DataResponse(ResponseCode.SUCCESS.getCode(), "", metaService.selectAllTags());
    }

    /**
     * getCategorysByPage 分页获取分类信息
     */
    @RequestMapping(value = {"/admin/category/page/{pageNo}"})
    public DataResponse getCategorysByPage(@PathVariable int pageNo, @RequestParam int parent) {
        PageInfo pageInfo = new PageInfo();
        pageInfo = metaService.selectCategorysByPageAndParent(pageNo, 5, parent);
        return new DataResponse(ResponseCode.SUCCESS.getCode(), "", pageInfo);
    }

    /**
     * saveMeta 保存元素
     */
    @RequestMapping(value = {"/admin/save/meta"})
    public DataResponse saveMeta(@RequestBody Metas meta) {
        if (meta.getMid() != null){
            metaService.updateMetas(meta);
        }else{
            metaService.insertSelective(meta);
        }
        return new DataResponse(ResponseCode.SUCCESS.getCode(), "保存元素成功", null);
    }
}