package com.dofast.module.system.oneLogin.service;

import com.dofast.module.system.oneLogin.domain.InitLoginVo;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public interface OneLoginService {

    /***
     * 初始化 页面
     * @return
     */
    List<InitLoginVo> initOneLoginInfo();

    /***
     * 根据系统需要，设置账号和密码 或者 app_key 和 电话号码
     * @param map
     */
    void register(Map<String, String> map);


    /***
     * 澳美MES登录
     * @param username
     * @param password
     * @return
     */
    String getAmAccessToken(String username, String password,String net);


    /***
     * 智源HR
     * @param userName
     * @param password
     * @return
     */
    String zyHrAutoLogin(String userName, String password,String net) throws JsonProcessingException;


    /***
     * 东合MES登录
     * @param appId
     * @param account
     * @return
     */
    String getDhAccessToken(String appId, String account,String net) throws JsonProcessingException;


    /***
     * 智源MES登录
     * @param appId
     * @param account
     * @return
     */
    String zyMesAutoLogin(String appId, String account,String net) throws JsonProcessingException;

    /***
     * 智源MES 请求接口 1.1
     * @param account
     * @return
     */
    Map<String, String> zyToken(String account,String net) throws JsonProcessingException;


    /***
     * 智源MES 请求接口 1.2
     *
     * @param map
     * @return
     * @throws Exception
     */
    Map<String, String> zyNewToken(String accessToken, String userId, String net) throws JsonProcessingException;
}
