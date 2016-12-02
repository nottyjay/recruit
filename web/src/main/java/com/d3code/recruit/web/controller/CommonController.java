package com.d3code.recruit.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jileilei on 2016/12/2.
 */
@Controller
public class CommonController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "/common/login";
    }
}
