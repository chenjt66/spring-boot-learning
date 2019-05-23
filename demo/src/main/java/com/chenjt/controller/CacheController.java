package com.chenjt.controller;

import com.chenjt.model.POJO.UserInfo;
import com.chenjt.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chen jianting on 2019/5/8.
 */
@RestController
public class CacheController {
  @Autowired
  private CacheService cacheService;

  @RequestMapping(value = "/app/cache", method = RequestMethod.GET)
  public String getCache(@RequestParam(value = "name") String name){
    UserInfo result = cacheService.getUserInfo(name);
    System.out.println("=====================" + name + "===================");
    return result.toString();
  }
}
