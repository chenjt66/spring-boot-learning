package com.chenjt.web;

import com.chenjt.common.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by chenjianting on 2019/4/14
 **/
@RestController
public class HelloController {

    @Autowired
    private Properties properties;

    @RequestMapping("/hello")
    public String index(){
        return properties.getChineseName();
    }
}
