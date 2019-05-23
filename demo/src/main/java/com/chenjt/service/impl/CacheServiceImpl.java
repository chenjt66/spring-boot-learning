package com.chenjt.service.impl;

import com.chenjt.model.POJO.UserInfo;
import com.chenjt.service.CacheService;
import javafx.scene.layout.Priority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by chen jianting on 2019/5/8.
 */
@Service
public class CacheServiceImpl implements CacheService {

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private class Cash{
    private int amount;
    private String name;
  }

  @Override
  public UserInfo getUserInfo(String name) {
    System.out.println("========进入getUserInfo方法========");
    UserInfo userInfo = new UserInfo(name, name.length(), name.toLowerCase());
    PriorityQueue<Cash> priorityQueue = new PriorityQueue<>(new Comparator<Cash>() {
      @Override
      public int compare(Cash o1, Cash o2) {
        if (o1.getAmount() < o2.getAmount())
          return -1;
        else if (o1.getAmount() == o2.getAmount())
          return 0;
        else
          return 1;
      }
    });

    Cash cash = new Cash(50,"chen");
    Cash cash2 = new Cash(39,"wang");
    Cash cash3 = new Cash(20,"ye");
    Cash cash4 = new Cash(100,"ruan");
    priorityQueue.add(cash);
    priorityQueue.add(cash2);
    priorityQueue.add(cash3);
    priorityQueue.add(cash4);

    System.out.println("=============打印结果==============");
    System.out.println("name:" + name + "; age:" + name.length() + "; nickName:" + name.toLowerCase());
    return userInfo;
  }

  @Override
  public void putUserInfo(String name, UserInfo userInfo) {

  }
}
