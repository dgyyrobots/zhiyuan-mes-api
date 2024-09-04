package com.dofast.module.channel.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "dian3")
public class Dian3Config {

    //基础url
    public String baseUrl;

    //AppKey
    public String appKey;

    //AppSecret
    public String appSecret;

}
