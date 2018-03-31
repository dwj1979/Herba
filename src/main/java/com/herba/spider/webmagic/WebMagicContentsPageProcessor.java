package com.herba.spider.webmagic;

import com.herba.model.dto.SpiderTaskConfig;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

/**
 * WebMagicContentsPageProcessor 爬虫页面处理类
 */
public class WebMagicContentsPageProcessor implements us.codecraft.webmagic.processor.PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    private SpiderTaskConfig spiderTaskConfig;

    /**
     * process 页面抓取
     * */
    @Override
    public void process(Page page) {
        //eg: "https://blog\\.opmax\\.win/index\\.php/archives/\\w+/"
        page.addTargetRequests(page.getHtml().links().regex(spiderTaskConfig.getUrlRule()).all());
        WebMagicContents webMagicContents = new WebMagicContents();
        //eg:  "//*[@id=\"pjax-container\"]/div[4]/div[1]/div[1]/h2/text()"
        webMagicContents.setTitle(page.getHtml().xpath(spiderTaskConfig.getTitleRule()).toString());
        //eg:  "https://blog\\.opmax\\.win/index\\.php/archives/\\w+/"
        webMagicContents.setAuthor(page.getUrl().regex(spiderTaskConfig.getAuthorRule()).toString());
        //eg:  "//*[@id=\"pjax-container\"]/div[4]/div[1]/div[2]/div/article/section"
        webMagicContents.setText(page.getHtml().xpath(spiderTaskConfig.getTextRule()).toString());
        page.putField("repo", webMagicContents);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public WebMagicContentsPageProcessor(SpiderTaskConfig spiderTaskConfig) {
        this.spiderTaskConfig = spiderTaskConfig;
    }
}
