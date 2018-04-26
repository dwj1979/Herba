package com.herba.controller;


import com.github.pagehelper.PageInfo;
import com.herba.model.dto.ContentSave;
import com.herba.model.dto.DataResponse;
import com.herba.model.dto.ResponseCode;
import com.herba.model.entity.Relationships;
import com.herba.service.ContentService;
import com.herba.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * ContentsController   文章api控制器
 *
 * @author Varshonwood
 * @version 1.0
 * @date 17/11/07
 */

@RestController
public class ContentsController {

    /**
     * 文章服务类
     */
    @Autowired
    ContentService contentService;
    @Autowired
    MetaService metaService;

    /**
     * getPostByPage   根据分页获得文章列表信息
     *
     * @param pageNo 请求页数
     * @return
     */
    @RequestMapping(value = "/admin/post/page/{pageNo}")
    public DataResponse getPostByPage(@PathVariable int pageNo) {
        PageInfo pageInfo = new PageInfo();
        pageInfo = contentService.selectPost(pageNo, 5);
        return new DataResponse(ResponseCode.SUCCESS.getCode(), "", pageInfo);
    }

    /**
     * getPageByPage   根据分页获得页面列表信息
     *
     * @param pageNo 请求页数
     * @return
     */
    @RequestMapping(value = "/admin/page/page/{pageNo}")
    public DataResponse getPageByPage(@PathVariable int pageNo) {
        PageInfo pageInfo = new PageInfo();
        pageInfo = contentService.selectPage(pageNo, 5);
        return new DataResponse(ResponseCode.SUCCESS.getCode(), "", pageInfo);
    }

    /**
     * getPostByPage   根据分页和元素id获得文章列表信息
     *
     * @param mid    元素id
     * @param pageNo 请求页数
     * @return
     */
    @RequestMapping(value = "/admin/mid/{mid}/{pageNo}")
    public DataResponse getPostByPageAndMid(@PathVariable int pageNo, @PathVariable int mid) {
        PageInfo pageInfo = new PageInfo();
        Relationships relationships = new Relationships();
        relationships.setMid(mid);
        pageInfo = contentService.selectPost(pageNo, 5, relationships);
        return new DataResponse(ResponseCode.SUCCESS.getCode(), "", pageInfo);
    }

    /**
     * getPostById   根据Id获得文章信息
     *
     * @param cid 文章ID
     * @return
     */
    @RequestMapping(value = "/admin/post/{cid}")
    public DataResponse getPostById(@PathVariable int cid) {
        if(cid==3){
            throw  new RuntimeException("cid为3不能查询");
        }
        return new DataResponse(ResponseCode.SUCCESS.getCode(), "", contentService.selectPostByPrimaryKey(cid));
    }

    /**
     * getPageById   根据Id获得页面信息
     *
     * @param cid 页面ID
     * @return
     */
    @RequestMapping(value = "/admin/page/{cid}")
    public DataResponse getPageById(@PathVariable int cid) {
        return new DataResponse(ResponseCode.SUCCESS.getCode(), "", contentService.selectPageByPrimaryKey(cid));
    }


    /**
     * savePost   保存文章
     *
     * @return
     */
    @RequestMapping(value = "/admin/save/post")
    public DataResponse savePost(@RequestBody ContentSave contents) {
        contentService.savePost(contents.getContents(), contents.getCategorysKey(), contents.getTags());
        return new DataResponse(ResponseCode.SUCCESS.getCode(), "保存文章成功", null);
    }

    /**
     * savePage   保存页面
     *
     * @return
     */
    @RequestMapping(value = "/admin/save/page")
    public DataResponse savePage(@RequestBody ContentSave contents) {
        contentService.savePage(contents.getContents());
        return new DataResponse(ResponseCode.SUCCESS.getCode(), "保存页面成功", null);
    }
    /**
     * deleteContent   删除页面或者页面
     *
     * @return
     */
    @RequestMapping(value = "/admin/delete/content")
    public DataResponse deleteContent(@RequestBody int cid) {
        contentService.deletePostOrPage(cid);
        return new DataResponse(ResponseCode.SUCCESS.getCode(), "删除页面或者页面成功", null);
    }
    /**
     * deleteContent   批量删除页面或者页面
     *
     * @return
     */
    @RequestMapping(value = "/admin/delete/contents")
    public DataResponse deleteMultipleContents(@RequestBody List<Integer> clist) {
        for(int cid:clist){
            contentService.deletePostOrPage(cid);
        }
        return new DataResponse(ResponseCode.SUCCESS.getCode(), "批量删除页面或者页面成功", null);
    }
}
