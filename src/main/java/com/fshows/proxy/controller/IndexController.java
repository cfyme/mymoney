package com.fshows.proxy.controller;

import com.fshows.proxy.contants.MyConstants;
import com.fshows.proxy.myutil.MyFileUtil;
import com.fshows.proxy.result.LearnResouce;
import com.fshows.proxy.result.ResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);


    @RequestMapping("/my")
    public String index(ModelMap map) {
        logger.info("=============index===========");
        // 加入一个属性，用来在模板中读取
        map.addAttribute("name", "test");
        return "index";
    }

    @RequestMapping("/user")
    public Map getUsers() {
        Map map = new HashMap<>();
        map.put("name","fangxiao");
        map.put("age","15");
        return map;
    }

    @RequestMapping("/hello")

    public String hello(Map<String,Object> map){

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("message", "这是测试的内容。。。");
        model.put("toUserName", "张三");
        model.put("fromUserName", "老许");
        return "welcome";
    }


    /**
     * 默认页<br/>
     * @RequestMapping("/") 和 @RequestMapping 是有区别的
     * 如果不写参数，则为全局默认页。
     * 如果加了参数“/”，则只认为是根页面。
     */
    @RequestMapping("/index")
    public ModelAndView index(Map<String, Object> model){
        logger.info("-----------index .---------------.....");

        List<LearnResouce> learnList =new ArrayList<LearnResouce>();
        LearnResouce bean =new LearnResouce("官方参考文档","Spring Boot Reference Guide","http://docs.spring.io/spring-boot/docs/1.5.1.RELEASE/reference/htmlsingle/#getting-started-first-application");
        learnList.add(bean);

        bean =new LearnResouce("程序猿DD","Spring Boot系列","http://www.roncoo.com/article/detail/125488");
        learnList.add(bean);

        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("learnList", learnList);

        return  modelAndView;
    }

}
