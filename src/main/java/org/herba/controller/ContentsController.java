package org.herba.controller;


import com.github.pagehelper.PageInfo;
import org.herba.model.dto.ContentDetail;
import org.herba.model.dto.ContentSave;
import org.herba.model.dto.ContentsInfo;
import org.herba.model.entity.Metas;
import org.herba.model.entity.Relationships;
import org.herba.service.ContentService;

import org.herba.service.MetaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public PageInfo getPostByPage(@PathVariable int pageNo) {
        PageInfo pageInfo = new PageInfo();
        pageInfo = contentService.selectPost(pageNo, 5);
        return pageInfo;
    }

    /**
     * getPageByPage   根据分页获得页面列表信息
     *
     * @param pageNo 请求页数
     * @return
     */
    @RequestMapping(value = "/admin/page/page/{pageNo}")
    public PageInfo getPageByPage(@PathVariable int pageNo) {
        PageInfo pageInfo = new PageInfo();
        pageInfo = contentService.selectPage(pageNo, 5);
        return pageInfo;
    }

    /**
     * getPostByPage   根据分页和元素id获得文章列表信息
     *
     * @param mid    元素id
     * @param pageNo 请求页数
     * @return
     */
    @RequestMapping(value = "/admin/mid/{mid}/{pageNo}")
    public PageInfo getPostByPageAndMid(@PathVariable int pageNo, @PathVariable int mid) {
        PageInfo pageInfo = new PageInfo();
        Relationships relationships = new Relationships();
        relationships.setMid(mid);
        pageInfo = contentService.selectPost(pageNo, 5, relationships);
        return pageInfo;
    }

    /**
     * getPostById   根据Id获得文章信息
     *
     * @param cid 文章ID
     * @return
     */
    @RequestMapping(value = "/admin/post/{cid}")
    public ContentsInfo getPostById(@PathVariable int cid) {
        return contentService.selectPostByPrimaryKey(cid);
    }

    /**
     * getPageById   根据Id获得页面信息
     *
     * @param cid 页面ID
     * @return
     */
    @RequestMapping(value = "/admin/page/{cid}")
    public ContentsInfo getPageById(@PathVariable int cid) {
        return contentService.selectPageByPrimaryKey(cid);
    }

    /**
     * savePost   保存文章
     *
     * @return
     */
    @RequestMapping(value = "/admin/save/post")
    public void savePost(@RequestBody ContentSave contents) {
            contentService.savePost(contents.getContents(), contents.getCategorysKey(), contents.getTags());
    }
    /**
     * savePage   保存页面
     *
     * @return
     */
    @RequestMapping(value = "/admin/save/page")
    public void savePage(@RequestBody ContentSave contents) {
        contentService.savePage(contents.getContents());
    }

}
