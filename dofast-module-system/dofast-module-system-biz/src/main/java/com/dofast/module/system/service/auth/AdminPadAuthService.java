package com.dofast.module.system.service.auth;

import com.dofast.framework.common.pojo.AjaxResult;
import com.dofast.module.system.controller.admin.auth.vo.*;
import com.dofast.module.system.controller.pad.login.vo.PadLoginParams;
import com.dofast.module.system.controller.pad.login.vo.PadLoginRes;
import com.dofast.module.system.dal.dataobject.user.AdminUserDO;

import java.util.HashMap;

/**
 * 管理后台的认证 Service 接口
 *
 * 提供用户的登录、登出的能力
 *
 * @author 芋道源码
 */
public interface AdminPadAuthService {

    /**
     * 验证账号 + 密码。如果通过，则返回用户
     *
     * @param username 账号
     * @param password 密码
     * @return 用户
     */
    AdminUserDO authenticate(String username, String password);

    /**
     * 账号登录
     *
     * @return 登录结果
     */
    AjaxResult login(PadLoginParams params);

    /**
     * 基于 token 退出登录
     *
     * @param token token
     * @param logType 登出类型
     */
    void logout(String token, Integer logType);

    /**
     * 短信验证码发送
     *
     * @param reqVO 发送请求
     */
    void sendSmsCode(AuthSmsSendReqVO reqVO);

    /**
     * 短信登录
     *
     * @return 登录结果
     */
    PadLoginRes smsLogin(PadLoginParams params) ;

    /**
     * 刷新访问令牌
     *
     * @param refreshToken 刷新令牌
     * @return 登录结果
     */
    AuthLoginRespVO refreshToken(String refreshToken);

}
