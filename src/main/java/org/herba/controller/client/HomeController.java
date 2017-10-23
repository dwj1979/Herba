package org.herba.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/client")
public class HomeController {
    @RequestMapping(value = "/home")
    public ModelAndView index(){
        ModelAndView modelAndView=new ModelAndView("/client/home");
        System.out.println("用户进入后台主界面");
        return  modelAndView;
    }
}
