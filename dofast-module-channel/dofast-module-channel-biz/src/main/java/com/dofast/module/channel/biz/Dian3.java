package com.dofast.module.channel.biz;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.dofast.module.channel.config.Dian3Config;
import dm.jdbc.util.StringUtil;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Dian3 {
    public Boolean debug = false;
    public String baseUrl;
    public String appKey;
    public String appSecret;
    public String timestamp;

    public String content;
    public String sign;
    public String method;
    public HashMap paramMap;
    public String  paramJson;

    @Autowired
    Dian3Config dian3Config;


    public void defaultParam() {
        if (paramMap.get("pageNo")==null ||StringUtil.isEmpty(paramMap.get("pageNo").toString())) paramMap.put("pageNo", 1);
        if (paramMap.get("pageSize")==null ||StringUtil.isEmpty(paramMap.get("pageSize").toString())) paramMap.put("pageSize", 50);
        if (paramMap.get("startTime")==null ||StringUtil.isEmpty(paramMap.get("startTime").toString())) paramMap.put("startTime", DateUtil.beginOfDay(new Date() ));
        if (paramMap.get("endTime")==null || StringUtil.isEmpty(paramMap.get("endTime").toString())) paramMap.put("endTime", DateUtil.endOfDay(new Date()));
    }


    public Dian3 init() {
        baseUrl = dian3Config.getBaseUrl();
        appKey = dian3Config.getAppKey();
        appSecret = dian3Config.getAppSecret();
        timestamp = System.currentTimeMillis() + "";
//         defaultParam();
        return this;
    }

    public Dian3 init(String methodx, HashMap paramMapx) {
         init();
         method = methodx;
         paramMap = paramMapx;
//         defaultParam();
         return this;
    }


    public Dian3 init(String methodx, String timestampx ,HashMap paramMapx) {
        init();
        timestamp = timestampx + "";
        method = methodx;
        paramMap = paramMapx;
//        defaultParam();
        return this;
    }


    public String buildSign(String method, String timestamp, HashMap paramMap) {
        paramJson = JSONUtil.toJsonStr(paramMap);
//        String firstStr = appSecret + "appKey" + appKey+ "method" +method + "timestamp" +timestamp + paramJson + appSecret;
//        String firstStr = appSecret+"appKey" + appKey+ "method" +method+ "reqId"+paramMap.get("reqId")+"tag"+ paramMap.get("tag")+ "timestamp" +timestamp +paramMap.get("content")+appSecret;
//        String firstStr = appSecret + "appKey" + appKey+ "method" +method  + timestamp + paramJson + appSecret;
        String firstStr = appSecret + timestamp + appSecret;
        if (debug) System.out.println(firstStr);
        String sign =   DigestUtil.md5Hex(firstStr.getBytes(StandardCharsets.UTF_8));
        if (debug) System.out.println(sign);
        return sign;
    }
    public Dian3 buildSign() {
        sign = buildSign(method, timestamp, paramMap);
        return this;
    }
    public String doPost() {
        return doPost(baseUrl);
    }
    public String doPost(String _baseUrl) {
        String url = _baseUrl + "?method=" + method + "&appKey=" + appKey + "&timestamp=" + timestamp + "&sign=" + sign;
//        return HttpUtil.post(url, paramMap);
        return HttpUtil.post(url, paramJson);
    }

    public boolean checkSign(String signx) {
        if (sign.equals(signx)) {
            return true;
        }
        return false;
    }
}
