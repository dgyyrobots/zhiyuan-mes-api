package com.dofast.module.system.controller.admin.auth;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.tenant.core.context.TenantContextHolder;
import com.dofast.module.system.oneLogin.domain.InitLoginVo;
import com.dofast.module.system.oneLogin.service.OneLoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.xml.internal.ws.api.ha.HaInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dofast.framework.common.pojo.CommonResult.success;

@RestController
@RequestMapping("/system/oneLogin")
public class OneLoginController {

    @Resource
    private OneLoginService oneLoginService;

    /**
     * 初始化单点登录页面
     *
     * @return
     */
    @GetMapping("/initOneLoginInfo")
    public CommonResult<List<InitLoginVo>> initOneLoginInfo() {
        List<InitLoginVo> list = oneLoginService.initOneLoginInfo();
        return success(list);
    }

    /**
     * 设置账号和密码
     *
     * @param map
     * @return
     */
    @PostMapping("/register")
    public CommonResult<String> register(@RequestBody Map<String, String> map) {
        oneLoginService.register(map);
        return success("账号密码设置完成");
    }


    /**
     * 澳美MES登录
     *
     * @param map
     * @return
     */
    @PostMapping("/amMesAutoLogin")
    public CommonResult<Map<String, String>> amMesAutoLogin(@RequestBody Map<String, String> map) {
        String username = map.get("userName");
        String password = map.get("password");
        String net = map.get("net");
        //获取 accessToken 登录令牌
        String accessToken = oneLoginService.getAmAccessToken(username, password, net);
        String tenantId = TenantContextHolder.getTenantId().toString();
        Map<String, String> result = new HashMap<>();
        result.put("accessToken", accessToken);
        result.put("tenantId", tenantId);
        return success(result);
    }


    /**
     * 东合MES登录
     *
     * @param map
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("/dhMesAutoLogin")
    public CommonResult<String> dhMesAutoLogin(@RequestBody Map<String, String> map) throws JsonProcessingException {
        String appId = map.get("appId");
        String account = map.get("account");
        String net = map.get("net");
        String accessToken = oneLoginService.getDhAccessToken(appId, account, net);
        return success(accessToken);
    }


    /**
     * 智源MES登录
     *
     * @param map
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("/zyMesAutoLogin")
    public CommonResult<String> zyMesAutoLogin(@RequestBody Map<String, String> map) throws JsonProcessingException {
        String appId = map.get("appId");
        String account = map.get("account");
        String net = map.get("net");
        String accessToken = oneLoginService.zyMesAutoLogin(appId, account, net);
        return success(accessToken);
    }


    /**
     * 智源HR
     *
     * @param map
     * @return
     * @throws Exception
     */
    @PostMapping("/zyHrAutoLogin")
    public CommonResult<String> zyHrAutoLogin(@RequestBody Map<String, String> map) throws Exception {
        String userName = map.get("userName");
        String password = map.get("password");
        String net = map.get("net");
        String result = oneLoginService.zyHrAutoLogin(userName, password, net);
        return success(result);
    }

    /**
     * 智源MES 请求接口 1.1
     *
     * @param map
     * @return
     * @throws Exception
     */
    @PostMapping("/zyToken")
    public CommonResult<Map<String, String>> zyToken(@RequestBody Map<String, String> map) throws Exception {
        //String appId = map.get("appId");
        String account = map.get("account");
        String net = map.get("net");
        Map<String, String> result = oneLoginService.zyToken(account, net);
        return success(result);
    }

    /**
     * 智源MES 请求接口 1.2
     *
     * @param map
     * @return
     * @throws Exception
     */
    @PostMapping("/zyNewToken")
    public CommonResult<Map<String, String>> zyNewToken(@RequestBody Map<String, String> map) throws Exception {
        String accessToken = map.get("accessToken");
        String userId = map.get("userId");
        String net = map.get("net");
        Map<String, String> result = oneLoginService.zyNewToken(accessToken, userId, net);
        return success(result);
    }

}






































