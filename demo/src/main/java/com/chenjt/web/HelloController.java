package com.chenjt.web;

import com.chenjt.common.Properties;
import com.chenjt.model.UserBasePO;
import com.chenjt.model.UserBaseRepository;
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

    @Autowired
    private UserBaseRepository userBaseRepository;

    @RequestMapping("/hello")
    public String index(){
        UserBasePO userBase = userBaseRepository.findByPhoneAndIsActive("111", 1);
        return properties.getChineseName();
    }
}
