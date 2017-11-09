package org.herba.controller;

import java.util.List;

import org.herba.model.dto.ContentsInfo;
import org.herba.service.ContentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * ContentsController   文章api控制器
 *
 * @version        1.0
 * @author         Varshonwood
 * @date           17/11/07
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class ContentsController {

    /** 文章服务类 */
    @Autowired
    ContentService contentService;

    /**
     * getPostByPage   根据分页获得文章信息
     * @param  type     请求页面类型
     * @param  pageNo   请求页数
     * @return
     */
    @RequestMapping(value = "/admin/{type}/page/{pageNo}")
    public List<ContentsInfo> getPostByPage(@PathVariable int pageNo, @PathVariable String type, HttpServletResponse response) {
        System.out.println("后台请求第"+pageNo+"页"+(type.equals("post")?"文章":"页面")+"列表");
        List<ContentsInfo> contentsList= contentService.selectByType(pageNo,3,type);
        if (contentsList.size() ==0){
            response.setStatus(204);
        }else {
            response.setStatus(200);
        }
        return  contentsList;
    }
}
//~ Formatted by Jindent --- http://www.jindent.comß
