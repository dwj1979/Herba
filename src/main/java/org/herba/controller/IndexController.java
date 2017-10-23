package org.herba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
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
}
