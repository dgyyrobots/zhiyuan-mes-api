package com.dofast.module.system.controller.admin.wechat;

import com.alibaba.fastjson.JSON;
import com.dofast.framework.common.exception.ErrorCode;
import com.dofast.module.system.controller.admin.wechat.vo.WechatAccessTokenResponse;
import com.dofast.module.system.controller.admin.wechat.vo.WechatDepartment;
import com.dofast.module.system.controller.admin.wechat.vo.WechatDepartmentResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;

@Component
public class WechatGetDept {
    private String clientId;

    private String clientSecret;

    private Integer agentId;

    private OkHttpClient client = new OkHttpClient();

    public List<WechatDepartment> getDepartmentList(Long parentId) throws IOException {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/simplelist";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("access_token", getAccessToken());
        if (parentId != null) {
            urlBuilder.addQueryParameter("id", String.valueOf(parentId));
        }
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .build();
        // 发送请求并获取响应
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        Map<String, Object> result = new Gson().fromJson(responseBody, new TypeToken<Map<String, Object>>() {}.getType());
        if (result.containsKey("errcode") && (int) Double.parseDouble(result.get("errcode").toString()) == 0) {
            // 请求成功，返回信息
            WechatDepartmentResponse wechatDepartmentResponse = new Gson().fromJson(responseBody, WechatDepartmentResponse.class);
            return wechatDepartmentResponse.getDepartments();
        } else {
            Object resultCode = result.get("errcode");
            if (resultCode == null) {
                resultCode = new Integer(-1);
            }
            // 请求失败，返回错误信息
            throw exception(new ErrorCode((int) Double.parseDouble(resultCode.toString()) , result.get("errmsg").toString()));
        }
    }



    private String getAccessToken() throws IOException {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("corpid", clientId);
        urlBuilder.addQueryParameter("corpsecret", clientSecret);
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String responseBody = response.body().string();
            WechatAccessTokenResponse accessTokenResponse = JSON.parseObject(responseBody, WechatAccessTokenResponse.class);
            if (accessTokenResponse.getErrcode() != 0) {
                throw new IOException("Failed to get access token: " + accessTokenResponse.getErrmsg());
            }
            return accessTokenResponse.getAccess_token();
        }
    }
}
