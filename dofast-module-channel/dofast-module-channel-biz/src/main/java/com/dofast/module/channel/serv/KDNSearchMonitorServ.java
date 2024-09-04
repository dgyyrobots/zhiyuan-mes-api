package com.dofast.module.channel.serv;

import com.dofast.framework.common.exception.ErrorCode;
import com.dofast.module.channel.kndpojo.searchmonitor.KDNSearchMonitorReq;
import com.dofast.module.channel.kndpojo.searchmonitor.KDNSearchMonitorRes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;

@Component
public class KDNSearchMonitorServ {
    // 从配置文件中读取快递鸟API的AppKey和EBusinessID
    @Value("${kdniao.appKey}")
    private String apiKey;

    @Value("${kdniao.eBusinessID}")
    private String eBusinessID;

    // 注入OkHttpClient对象，用于发起HTTP请求
    @Resource
    private OkHttpClient okHttpClient;

    /**
     * MD5加密
     * str 内容
     * charset 编码方式
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private String MD5(String str,String charset) throws Exception {
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
     * @throws UnsupportedEncodingException
     */
    private String base64(String str, String charset) throws UnsupportedEncodingException{
        String encoded = Base64.encode(str.getBytes(charset));
        return encoded;
    }

    @SuppressWarnings("unused")
    private String urlEncoder(String str, String charset) throws UnsupportedEncodingException{
        String result = URLEncoder.encode(str, charset);
        return result;
    }

    /**
     * 电商Sign签名生成
     * content 内容
     * keyValue ApiKey
     * charset 编码方式
     * @throws UnsupportedEncodingException ,Exception
     * @return DataSign签名
     */
    @SuppressWarnings("unused")
    private  String encrypt (String content,String keyValue,String charset) throws UnsupportedEncodingException, Exception
    {
        if (keyValue != null)
        {
            return base64(MD5(content + keyValue, charset), charset);
        }
        return base64(MD5(content, charset), charset);
    }


    public KDNSearchMonitorRes getKDNESearchMonitor(KDNSearchMonitorReq searchMonitorReq) throws Exception {
        String url = "https://api.kdniao.com/api/dist";
        // 创建Gson对象
        Gson gson = new Gson();
        // 将params对象转换为JSON字符串
        Map<String, String> param = toHashMap(searchMonitorReq);
        String requestData = gson.toJson(param);
        // 组装系统级参数
        Map<String,String> params = new HashMap<String,String>();
        params.put("RequestData", urlEncoder(requestData, "UTF-8"));
        params.put("EBusinessID", eBusinessID);
        params.put("RequestType", "8001");
        String dataSign=encrypt(requestData, apiKey, "UTF-8");
        params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
        params.put("DataType", "2");
        // 以form表单形式提交post请求，post请求体中包含了应用级参数和系统级参数
        KDNSearchMonitorRes data = sendPost(url, params);
        return data;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * url 发送请求的 URL
     * params 请求的参数集合
     * @return 远程资源的响应结果
     */
    private KDNSearchMonitorRes sendPost(String url, Map<String,String> params) throws IOException {
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
            // 请求成功，返回信息
            KDNSearchMonitorRes data = new Gson().fromJson(responseBody, KDNSearchMonitorRes.class);
            return data;
        } else {
            String message = String.valueOf(result.get("Reason"));
            if (message == null) {
                message = "轨迹查询失败";
            }
            // 请求失败，返回错误信息
            throw exception(new ErrorCode(3049004, message));
        }
    }

    //类转化为参数
    private Map<String, String> toHashMap(KDNSearchMonitorReq searchMonitorReq) {
        // 设置KDNEOrderReq对象的属性值
        Map<String, String> paramMap = new HashMap<>();
        Gson gson = new Gson();
        paramMap.put("LogisticCode", searchMonitorReq.getLogisticCode());
        paramMap.put("ShipperCode", searchMonitorReq.getShipperCode());
        if (searchMonitorReq.getCustomerName() != null) {
            paramMap.put("CustomerName", searchMonitorReq.getCustomerName());
        }
        if (searchMonitorReq.getOrderCode() != null) {
            paramMap.put("OrderCode", searchMonitorReq.getOrderCode());
        }
        if (searchMonitorReq.getLogisticCode() != null) {

        }
        return paramMap;
    }
}
