package com.dofast.module.infra.service.address;

import cn.hutool.core.codec.Base64;
import com.dofast.module.infra.controller.admin.address.vo.AddressData;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


import java.io.*;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.security.MessageDigest;
import okhttp3.*;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.infra.enums.ErrorCodeConstants.ADDRESS_SOURCE_ERROR;

@Service
public class AddressKDNServiceImpl implements AddressKDNService {

    // 从配置文件中读取快递鸟API的AppKey和EBusinessID
    @Value("${kdniao.appKey}")
    private String apiKey;

    @Value("${kdniao.eBusinessID}")
    private String eBusinessID;

    // 注入OkHttpClient对象，用于发起HTTP请求
    @Autowired
    private OkHttpClient okHttpClient;

    @SneakyThrows
    @Override
    public AddressData getAddressData(String content) throws IOException {
        String url = "https://api.kdniao.com/api/dist";
        String requestData = "{\"Content\": \"" + content  + "\"}";
        // 组装系统级参数
        Map<String,String> params = new HashMap<String,String>();
        params.put("RequestData", urlEncoder(requestData, "UTF-8"));
        params.put("EBusinessID", eBusinessID);
        params.put("RequestType", "6001");
        String dataSign=encrypt(requestData, apiKey, "UTF-8");
        params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
        params.put("DataType", "2");
        // 以form表单形式提交post请求，post请求体中包含了应用级参数和系统级参数
        AddressData data = sendPost(url, params);
        return data;
    }

    /**
     * MD5加密
     * str 内容
     * charset 编码方式
     *
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private String MD5(String str, String charset) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(charset));
        byte[] result = md.digest();
        StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val <= 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toLowerCase();
    }

    /**
     * base64编码
     * str 内容
     * charset 编码方式
     *
     * @throws UnsupportedEncodingException
     */
    private String base64(String str, String charset) throws UnsupportedEncodingException {
        String encoded = Base64.encode(str.getBytes(charset));
        return encoded;
    }

    @SuppressWarnings("unused")
    private String urlEncoder(String str, String charset) throws UnsupportedEncodingException {
        String result = URLEncoder.encode(str, charset);
        return result;
    }

    /**
     * 电商Sign签名生成
     * content 内容
     * keyValue ApiKey
     * charset 编码方式
     *
     * @return DataSign签名
     * @throws UnsupportedEncodingException ,Exception
     */
    @SuppressWarnings("unused")
    private String encrypt(String content, String keyValue, String charset) throws UnsupportedEncodingException, Exception {
        if (keyValue != null) {
            return base64(MD5(content + keyValue, charset), charset);
        }
        return base64(MD5(content, charset), charset);
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * url 发送请求的 URL
     * params 请求的参数集合
     * @return 远程资源的响应结果
     */
    private  AddressData sendPost(String url, Map<String,String> params) throws IOException {
        // 创建OkHttpClient对象并构建请求
        FormBody.Builder formBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            formBuilder.add(entry.getKey(), entry.getValue());
        }
        RequestBody requestBody = formBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                .build();

        // 发送请求并获取响应
        Response response = okHttpClient.newCall(request).execute();
        String responseBody = response.body().string();
        Map<String, Object> result = new Gson().fromJson(responseBody, new TypeToken<Map<String, Object>>() {}.getType());
        if ("true".equalsIgnoreCase(result.get("Success").toString())) {
            // 请求成功，返回地址解析结果 将 Data 字段转换为 AddressData 类型的实例
            String dataJsonString = new Gson().toJson(result.get("Data"));
            Type type = new TypeToken<AddressData>(){}.getType();
            AddressData data = new Gson().fromJson(dataJsonString, type);
            return data;
        } else {
            // 请求失败，返回错误信息
            throw exception(ADDRESS_SOURCE_ERROR);
        }
    }
}

