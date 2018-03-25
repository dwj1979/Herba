package com.herba.controller;

import com.herba.model.dto.DataResponse;
import com.herba.model.dto.ResponseCode;
import com.herba.model.dto.SpiderTask;
import com.herba.spider.SpiderContentsInfoPipeline;
import com.herba.spider.SpiderContentsPageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;


@RestController
public class SpiderController {
    @Autowired
    SpiderContentsInfoPipeline spiderContentsInfoPipeline;

    /**
     * addSpiderPipeline 新增爬虫任务
     *
     * @param spiderTask 爬虫任务配置
     */
    @RequestMapping(value = "/admin/add/spider")
    public DataResponse addSpiderPipeline(@RequestBody SpiderTask spiderTask) {
        Spider.create(new SpiderContentsPageProcessor(spiderTask))
                //后续处理
                .addPipeline(spiderContentsInfoPipeline)
                //开始页面
                .addUrl(spiderTask.getStartUrl())
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
        return new DataResponse(ResponseCode.SUCCESS.getCode(), "", "");
    }
}
