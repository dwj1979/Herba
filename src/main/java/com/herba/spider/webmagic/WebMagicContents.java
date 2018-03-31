package com.herba.spider.webmagic;

import lombok.Data;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

@Data
@TargetUrl("https://blog.opmax.win/index.php/archives/*/")
public class WebMagicContents {
    @ExtractBy(value = "//*[@id=\"pjax-container\"]/div[4]/div[1]/div[1]/h2/text()")
    private String title;
    @ExtractByUrl("https://blog.opmax.win/index.php/archives/*/")
    private String author;
    @ExtractBy(value = "//*[@id=\"pjax-container\"]/div[4]/div[1]/div[2]/div/article/section")
    private String text;
}
