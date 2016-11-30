package com.d3code.recruit.web.controller;

import com.d3code.recruit.gather.service.WebHttpService;
import com.d3code.recruit.kafka.config.KafkaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nottyjay on 2016/11/29 0029.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @Resource(name = "lagouWebHttpServiceImpl")
    private WebHttpService lagouWebHttpService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        return "index/index";
    }

    @RequestMapping(value = "/spider", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> spider(String keyword){
        try {
            Map<String, String> lagouParamsMap = new HashMap<>();
            lagouParamsMap.put("first", "true");
            lagouParamsMap.put("pn", "1");
            lagouParamsMap.put("kd", keyword);
            lagouWebHttpService.loadPageSourceCode("http://www.lagou.com/jobs/positionAjax.json?px=default&city=上海&needAddtionalResult=false", lagouParamsMap);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
