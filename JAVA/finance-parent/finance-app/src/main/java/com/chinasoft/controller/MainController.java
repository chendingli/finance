package com.chinasoft.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lizhi
 * @Date: 2018/7/26 15:20
 */
@RestController
public class MainController {

    @RequestMapping("/index")
    public Object main(){
        return "index";
    }
}
