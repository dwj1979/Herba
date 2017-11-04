package org.herba.controller;

import org.herba.model.entity.Contents;
import org.herba.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

//开启ajax跨域访问

@CrossOrigin
@Controller
public class IndexController {
    @Autowired
    ContentService contentService;
    @RequestMapping(value = "/index")
    public ModelAndView index(){
        ModelAndView modelAndView=new ModelAndView("/index");
        System.out.println("");
        return modelAndView;
    }
    @RequestMapping(value = "/login")
    public ModelAndView login(){
        ModelAndView modelAndView=new ModelAndView("/login");
        System.out.println("用户进入登陆界面");
        return modelAndView;
    }
    @RequestMapping(value = "/api/post/{cid}")
    @ResponseBody
    public Contents getContentByCid(@PathVariable int cid){
        System.out.println("开始读取数据");
        Contents contents=contentService.GetContentByCid(cid);
        System.out.println(contents.toString());
        return contents;
    }
}
