package org.herba.controller;


import com.github.pagehelper.PageInfo;
import org.herba.model.dto.ContentDetail;
import org.herba.model.dto.ContentSave;
import org.herba.model.dto.ContentsInfo;
import org.herba.model.entity.Metas;
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
     * getPostByPage   根据分页获得文章或页面列表信息信息
     *
     * @param type   请求页面类型
     * @param pageNo 请求页数
     * @return
     */
    @RequestMapping(value = "/admin/{type}/page/{pageNo}")
    public PageInfo getPostByPage(@PathVariable int pageNo, @PathVariable String type, HttpServletRequest request, HttpServletResponse response) {
        PageInfo pageInfo = new PageInfo();
        if (type.equals("post")) {
            pageInfo = contentService.selectPost(pageNo, 5);

        } else {
            pageInfo = contentService.selectPage(pageNo, 5);
        }
        return pageInfo;
    }

    /**
     * getPostById   根据Id获得文章或信息
     *
     * @param type 请求页面类型
     * @param cid  文章ID
     * @return
     */
    @RequestMapping(value = "/admin/{type}/{cid}")
    public ContentDetail getPostById(@PathVariable int cid, @PathVariable String type, HttpServletRequest request, HttpServletResponse response) {
        ContentDetail contentDetail = new ContentDetail();
        if (type.equals("post")) {
            ContentsInfo contentsInfo = contentService.selectPostByPrimaryKey(cid);
            List<Metas> metasList = metaService.selectAll();
            contentDetail.setContentsInfo(contentsInfo);
            contentDetail.setMetas(metasList);

        } else {
            ContentsInfo contentsInfo = contentService.selectPageByPrimaryKey(cid);
            contentDetail.setContentsInfo(contentsInfo);
        }
        return contentDetail;
    }

    /**
     * saveContent   保存文章或者页面
     *
     * @param type 请求页面类型
     * @return
     */
    @RequestMapping(value = "/admin/save/{type}")
    public void saveContent(@PathVariable String type, @RequestBody ContentSave contents, HttpServletRequest request, HttpServletResponse response) {
        if (type.equals("post")) {
            contentService.savePost(contents.getContents(), contents.getCategorysKey(), contents.getTags());
        } else {
            contentService.savePage(contents.getContents());
        }
    }
}
