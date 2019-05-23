package com.chenjt.service;

import com.chenjt.model.POJO.UserInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by chen jianting on 2019/5/8.
 */
@CacheConfig(cacheNames = "user")
public interface CacheService {
  @Cacheable
  UserInfo getUserInfo(String name);

  @CachePut(key = "#p0")
  void putUserInfo(String name, UserInfo userInfo);
}
