package com.herba.controller;

import com.herba.model.dto.DataResponse;
import com.herba.model.dto.ResponseCode;
import com.herba.model.dto.SpiderTaskConfig;
import com.herba.model.dto.SpiderTaskInfo;
import com.herba.spider.webmagic.WebMagicContentsInfoPipeline;
import com.herba.spider.webmagic.WebMagicContentsPageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@RestController
public class SpiderController {
    @Autowired
    WebMagicContentsInfoPipeline webMagicContentsInfoPipeline;

    /**
     * addSpiderPipeline 新增爬虫任务
     *
     * @param spiderTaskConfig 爬虫任务配置
     *                         请求示例                  {
     *                         "startUrl":"https://blog.opmax.win/index.php/archives/45/",
     *                         "urlRule":"https://blog\\.opmax\\.win/index\\.php/archives/\\w+/",
     *                         "titleRule":"//*[@id=\"pjax-container\"]/div[4]/div[1]/div[1]/h2/text()",
     *                         "authorRule":"https://blog\\.opmax\\.win/index\\.php/archives/\\w+/",
     *                         "textRule":"//*[@id=\"pjax-container\"]/div[4]/div[1]/div[2]/div/article/section"
     *                         }
     */
    @RequestMapping(value = "/admin/spider/webmagic/add")
    public DataResponse addSpiderPipeline(@RequestBody SpiderTaskConfig spiderTaskConfig) throws Exception {
        Spider spider = Spider.create(new WebMagicContentsPageProcessor(spiderTaskConfig))
                //后续处理
                .addPipeline(webMagicContentsInfoPipeline)
                //开始页面
                .addUrl(spiderTaskConfig.getStartUrl())
                //开启5个线程抓取
                .thread(5);
        SpiderMonitor.instance().register(spider);
        spider.start();
        return new DataResponse(ResponseCode.SUCCESS.getCode(), "", "");
    }

    @RequestMapping(value = "/admin/spider/webmagic/monitor")
    public DataResponse spiderMonitor(int port) throws Exception {
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:" + port + "/jmxrmi");
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
        Set<ObjectInstance> mb = mbsc.queryMBeans(null, null);
        List<SpiderTaskInfo> spiderTaskInfos = new ArrayList<>();
        for (ObjectInstance objectInstance : mb) {
            ObjectName objectName = objectInstance.getObjectName();
            if (String.valueOf(objectName.getCanonicalName()).matches("WebMagic\\S+")){
                SpiderTaskInfo spiderTaskInfo = new SpiderTaskInfo();
                spiderTaskInfo.setName( mbsc.getAttribute(objectName, "Name").toString());
                spiderTaskInfo.setStartTime( mbsc.getAttribute(objectName, "StartTime").toString());
                spiderTaskInfo.setErrorPages(mbsc.getAttribute(objectName, "ErrorPages").toString());
                spiderTaskInfo.setStatus(mbsc.getAttribute(objectName, "Status").toString());
                spiderTaskInfo.setThread((int) mbsc.getAttribute(objectName, "Thread"));
                spiderTaskInfo.setSuccessPageCount((int) mbsc.getAttribute(objectName, "SuccessPageCount"));
                spiderTaskInfo.setErrorPageCount((int) mbsc.getAttribute(objectName, "ErrorPageCount"));
                spiderTaskInfo.setTotalPageCount((int) mbsc.getAttribute(objectName, "TotalPageCount"));
                spiderTaskInfo.setPagePerSecond((int) mbsc.getAttribute(objectName, "PagePerSecond"));
                spiderTaskInfo.setErrorPageCount((int) mbsc.getAttribute(objectName, "LeftPageCount"));
                spiderTaskInfos.add(spiderTaskInfo);
            }
        }
        jmxc.close();
        return new DataResponse(ResponseCode.SUCCESS.getCode(), "", spiderTaskInfos);
    }

    @RequestMapping(value = "/admin/spider/webmagic/stop")
    public DataResponse spiderStopTask(String name, int port) throws Exception {
        try {
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:" + port + "/jmxrmi");
            JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
            MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
            ObjectName mbeanName = new ObjectName("WebMagic:name=" + name);
            mbsc.invoke(mbeanName, "stop", null, null);
            jmxc.close();
        }catch (Exception e){
            throw e;
        }finally {
            return new DataResponse(ResponseCode.SUCCESS.getCode(), "", "");
        }
    }
    @RequestMapping(value = "/admin/spider/webmagic/start")
    public DataResponse spiderStartTask(String name, int port){
        try {
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:" + port + "/jmxrmi");
            JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
            MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
            ObjectName mbeanName = new ObjectName("WebMagic:name=" + name);
            mbsc.invoke(mbeanName, "start", null, null);
            jmxc.close();
        }catch (Exception e){
            throw e;
        }finally {
            return new DataResponse(ResponseCode.SUCCESS.getCode(), "", "");
        }
    }
}
