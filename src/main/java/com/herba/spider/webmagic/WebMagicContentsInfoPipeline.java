package com.herba.spider.webmagic;

import com.herba.model.entity.Metas;
import com.herba.service.ContentService;
import com.herba.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;


@Component
public class WebMagicContentsInfoPipeline implements Pipeline {
    @Autowired
    ContentService contentService;
    @Autowired
    MetaService metaService;
    /**
     *  process 爬虫抓取后处理
     * */
    @Override
    public void process(ResultItems resultItems, Task task) {
        WebMagicContents webMagicContents = (WebMagicContents) resultItems.get("repo");
        Metas metas = metaService.selectCategoryByName("爬虫");
        contentService.savePostByWebMagicSpider(webMagicContents, metas);
    }
}
