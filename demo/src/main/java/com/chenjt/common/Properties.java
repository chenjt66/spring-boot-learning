package com.chenjt.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * created by chenjianting on 2019/4/14
 **/
@Data
@Component
public class Properties {

    @Value("${chinese.name}")
    private String chineseName;

    @Value("${english.name}")
    private String englishName;
}
