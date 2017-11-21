package org.herba.controller;


import com.github.pagehelper.PageInfo;
import org.herba.model.dto.ContentDetail;
import org.herba.model.dto.ContentSave;
import org.herba.model.dto.ContentsInfo;
import org.herba.model.entity.Contents;
import org.herba.model.entity.Metas;
import org.herba.service.ContentService;

import org.herba.service.MetaService;
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

@CrossOrigin
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
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "后台请求第" + pageNo + "页" + (type.equals("post") ? "文章" : "页面") + "列表");
        PageInfo pageInfo = new PageInfo();
        if (type.equals("post")) {
            pageInfo = contentService.selectPost(pageNo, 5);

        } else {
            pageInfo = contentService.selectPage(pageNo, 5);
        }
        if (pageInfo.getList().size() == 0) {
            response.setStatus(204);
        } else {
            response.setStatus(200);
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
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "后台请求id 为" + cid + "的" + (type.equals("post") ? "文章" : "页面") + "列表");
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
        if (contentDetail.getContentsInfo() == null) {
            response.setStatus(204);
        } else {
            response.setStatus(200);
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
        int code = 0;
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "后台请求保存id为" + contents.getContents().getCid() + "的文章");
        if (type.equals("post")) {
            code = contentService.savePost(contents.getContents(), contents.getCategorysKey(), contents.getTags());
        }else {
            code = contentService.savePage(contents.getContents());
        }
        response.setStatus(code);
    }
}
