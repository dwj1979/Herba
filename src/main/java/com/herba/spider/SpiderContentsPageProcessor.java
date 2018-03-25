package com.herba.spider;

import com.herba.model.dto.SpiderTask;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

/**
 * SpiderContentsPageProcessor 爬虫页面处理类
 */
public class SpiderContentsPageProcessor implements us.codecraft.webmagic.processor.PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    private SpiderTask spiderTask;

    /**
     * process 页面抓取
     * */
    @Override
    public void process(Page page) {
        //eg: "https://blog\\.opmax\\.win/index\\.php/archives/\\w+/"
        page.addTargetRequests(page.getHtml().links().regex(spiderTask.getUrlRule()).all());
        SpiderContents spiderContents = new SpiderContents();
        //eg:  "//*[@id=\"pjax-container\"]/div[4]/div[1]/div[1]/h2/text()"
        spiderContents.setTitle(page.getHtml().xpath(spiderTask.getTitleRule()).toString());
        //eg:  "https://blog\\.opmax\\.win/index\\.php/archives/\\w+/"
        spiderContents.setAuthor(page.getUrl().regex(spiderTask.getAuthorRule()).toString());
        //eg:  "//*[@id=\"pjax-container\"]/div[4]/div[1]/div[2]/div/article/section"
        spiderContents.setText(page.getHtml().xpath(spiderTask.getTextRule()).toString());
        page.putField("repo", spiderContents);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public SpiderContentsPageProcessor(SpiderTask spiderTask) {
        this.spiderTask = spiderTask;
    }
}
