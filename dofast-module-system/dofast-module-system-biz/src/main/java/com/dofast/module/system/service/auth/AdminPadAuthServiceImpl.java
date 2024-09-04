package com.dofast.module.system.service.auth;

import cn.hutool.core.util.ObjectUtil;
import com.dofast.framework.common.enums.CommonStatusEnum;
import com.dofast.framework.common.enums.UserTypeEnum;
import com.dofast.framework.common.pojo.AjaxResult;
import com.dofast.framework.common.util.monitor.TracerUtils;
import com.dofast.framework.common.util.servlet.ServletUtils;
import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.framework.common.util.validation.ValidationUtils;
import com.dofast.framework.tenant.core.util.TenantUtils;
import com.dofast.framework.web.core.util.WebFrameworkUtils;
import com.dofast.module.system.api.logger.dto.LoginLogCreateReqDTO;
import com.dofast.module.system.api.sms.SmsCodeApi;
import com.dofast.module.system.controller.admin.auth.vo.AuthLoginReqVO;
import com.dofast.module.system.controller.admin.auth.vo.AuthLoginRespVO;
import com.dofast.module.system.controller.admin.auth.vo.AuthSmsLoginReqVO;
import com.dofast.module.system.controller.admin.auth.vo.AuthSmsSendReqVO;
import com.dofast.module.system.controller.pad.login.vo.PadLoginParams;
import com.dofast.module.system.controller.pad.login.vo.PadLoginRes;
import com.dofast.module.system.controller.pad.login.vo.PadLoginUser;
import com.dofast.module.system.convert.auth.AuthConvert;
import com.dofast.module.system.convert.user.UserConvert;
import com.dofast.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import com.dofast.module.system.dal.dataobject.user.AdminUserDO;
import com.dofast.module.system.enums.logger.LoginLogTypeEnum;
import com.dofast.module.system.enums.logger.LoginResultEnum;
import com.dofast.module.system.enums.oauth2.OAuth2ClientConstants;
import com.dofast.module.system.enums.sms.SmsSceneEnum;
import com.dofast.module.system.service.logger.LoginLogService;
import com.dofast.module.system.service.member.MemberService;
import com.dofast.module.system.service.oauth2.OAuth2TokenService;
import com.dofast.module.system.service.social.SocialUserService;
import com.dofast.module.system.service.user.AdminUserService;
import com.google.common.annotations.VisibleForTesting;
import com.xingyuv.captcha.model.common.ResponseModel;
import com.xingyuv.captcha.model.vo.CaptchaVO;
import com.xingyuv.captcha.service.CaptchaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Objects;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.util.servlet.ServletUtils.getClientIP;
import static com.dofast.module.system.enums.ErrorCodeConstants.*;

/**
 * Auth Service 实现类
 * Pad端只实现了账号密码登录，验证码登录可能无法应用，社交第三方登录未实现
 *
 * @author 芋道源码
 */
@Service
@Slf4j
public class AdminPadAuthServiceImpl implements AdminPadAuthService {

    @Resource
    private AdminUserService userService;
    @Resource
    private LoginLogService loginLogService;
    @Resource
    private OAuth2TokenService oauth2TokenService;
    @Resource
    private SocialUserService socialUserService;
    @Resource
    private MemberService memberService;
    @Resource
    private Validator validator;
    @Resource
    private CaptchaService captchaService;
    @Resource
    private SmsCodeApi smsCodeApi;

    /**
     * 验证码的开关，默认为 true
     */
    /*@Value("${dofast.captcha.enable:false}")*/
    private Boolean captchaEnable = false;

    @Override
    public AdminUserDO authenticate(String username, String password) {
        final LoginLogTypeEnum logTypeEnum = LoginLogTypeEnum.LOGIN_USERNAME;
        // 校验账号是否存在
        AdminUserDO user = userService.getUserByUsername(username);
        Long tenantId = WebFrameworkUtils.getTenantId(ServletUtils.getRequest());
        if (user == null) {
            createLoginLog(null, username, logTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (tenantId != null && !tenantId.equals(user.getTenantId())) throw exception(PAD_AUTH_LOGIN_NOT_EXISTS);
        if (!userService.isPasswordMatch(password, user.getPassword())) {
            createLoginLog(user.getId(), username, logTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        // 校验是否禁用
        if (ObjectUtil.notEqual(user.getStatus(), CommonStatusEnum.ENABLE.getStatus())) {
            createLoginLog(user.getId(), username, logTypeEnum, LoginResultEnum.USER_DISABLED);
            throw exception(AUTH_LOGIN_USER_DISABLED);
        }
        return user;
    }

    @Override
    public AjaxResult login(PadLoginParams reqVO) {
        if (Objects.isNull(reqVO)) {
           throw exception(PAD_AUTH_LOGIN_PARAM_NOT_EXISTS);
        }
        String loginType = reqVO.getLoginType();
        if(StrUtils.isEmpty(loginType)){
            throw exception(PAD_AUTH_LOGIN_TYPE_NOT_EXISTS);
        }
        //登录方式0验证码登录，1用户名密码登录，2本机一键登录，3微信单点登录
        if(loginType.equals("0")){
            //return smsLogin(reqVO);
            String phoneNo = reqVO.getPhoneNo();
            if(StrUtils.isEmpty(phoneNo)){
                throw exception(PAD_AUTH_LOGIN_PASSWORD_NOT_EXISTS);
            }
            String validCode = reqVO.getValidCode();
            //2表示登录验证码，校验验证码合法性
            //sysSmsSendService.checkValidCode(phoneNo,validCode,"2");
            reqVO.setUsername(phoneNo);
            reqVO.setPassword("SSO_LOGIN");
        }else if(loginType.equals("1")){
            String password = reqVO.getPassword();
            if(StrUtils.isEmpty(password)){
                throw exception(PAD_AUTH_LOGIN_PASSWORD_NOT_EXISTS);
            }
        }
        // 校验验证码
        //validateCaptcha(reqVO);

        // 使用账号密码，进行登录
        AdminUserDO user = authenticate(reqVO.getUsername(), reqVO.getPassword());
        PadLoginUser padUser = UserConvert.INSTANCE.convert04(user);
        // 创建 Token 令牌，记录登录日志
        AuthLoginRespVO authLoginRespVO = createTokenAfterLoginSuccess(user.getId(), reqVO.getUsername(),
                LoginLogTypeEnum.LOGIN_USERNAME);

        AjaxResult ajax = AjaxResult.success("");
        ajax.put("token",authLoginRespVO.getAccessToken());
        //token过期时间
        ajax.put("expired",authLoginRespVO.getExpiresTime());
        ajax.put("user",padUser);
        ajax.put("isAgent",String.valueOf(true));
        return ajax;
    }

    @Override
    public void sendSmsCode(AuthSmsSendReqVO reqVO) {
        // 登录场景，验证是否存在
        if (userService.getUserByMobile(reqVO.getMobile()) == null) {
            throw exception(AUTH_MOBILE_NOT_EXISTS);
        }
        // 发送验证码
        smsCodeApi.sendSmsCode(AuthConvert.INSTANCE.convert(reqVO).setCreateIp(getClientIP()));
    }

    @Override
    public PadLoginRes smsLogin(PadLoginParams params) {
        AuthSmsLoginReqVO reqVO = PadLoginToAuthSmsLogin(params);
        // 校验验证码
        smsCodeApi.useSmsCode(AuthConvert.INSTANCE.convert(reqVO, SmsSceneEnum.ADMIN_MEMBER_LOGIN.getScene(), getClientIP()));

        // 获得用户信息
        AdminUserDO user = userService.getUserByMobile(reqVO.getMobile());
        if (user == null) {
            throw exception(USER_NOT_EXISTS);
        }

        // 创建 Token 令牌，记录登录日志
        AuthLoginRespVO authLoginRespVO = createTokenAfterLoginSuccess(user.getId(), reqVO.getMobile(),
                LoginLogTypeEnum.LOGIN_MOBILE);
        PadLoginUser padUser = UserConvert.INSTANCE.convert04(user);
        PadLoginRes padLoginRes = new PadLoginRes();
        padLoginRes.setUser(padUser);
        padLoginRes.setExpired(authLoginRespVO.getExpiresTime());
        padLoginRes.setRefreshToken(authLoginRespVO.getRefreshToken());
        padLoginRes.setToken(authLoginRespVO.getAccessToken());
        return padLoginRes;
    }

    private void createLoginLog(Long userId, String username,
                                LoginLogTypeEnum logTypeEnum, LoginResultEnum loginResult) {
        // 插入登录日志
        LoginLogCreateReqDTO reqDTO = new LoginLogCreateReqDTO();
        reqDTO.setLogType(logTypeEnum.getType());
        reqDTO.setTraceId(TracerUtils.getTraceId());
        reqDTO.setUserId(userId);
        reqDTO.setUserType(getUserType().getValue());
        reqDTO.setUsername(username);
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        reqDTO.setUserIp(ServletUtils.getClientIP());
        reqDTO.setResult(loginResult.getResult());
        loginLogService.createLoginLog(reqDTO);
        // 更新最后登录时间
        if (userId != null && Objects.equals(LoginResultEnum.SUCCESS.getResult(), loginResult.getResult())) {
            userService.updateUserLogin(userId, ServletUtils.getClientIP());
        }
    }

    @VisibleForTesting
    void validateCaptcha(PadLoginParams params) {
        //转换为AuthLoginReqVO类
        AuthLoginReqVO reqVO = PadLoginToAuthLogin(params);
        // 如果验证码关闭，则不进行校验
        if (!captchaEnable) {
            return;
        }
        // 校验验证码
        ValidationUtils.validate(validator, reqVO, AuthLoginReqVO.CodeEnableGroup.class);
        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaVerification(reqVO.getCaptchaVerification());
        ResponseModel response = captchaService.verification(captchaVO);
        // 验证不通过
        if (!response.isSuccess()) {
            // 创建登录失败日志（验证码不正确)
            createLoginLog(null, reqVO.getUsername(), LoginLogTypeEnum.LOGIN_USERNAME, LoginResultEnum.CAPTCHA_CODE_ERROR);
            throw exception(AUTH_LOGIN_CAPTCHA_CODE_ERROR, response.getRepMsg());
        }
    }

    private AuthLoginRespVO createTokenAfterLoginSuccess(Long userId, String username, LoginLogTypeEnum logType) {
        // 插入登陆日志
        createLoginLog(userId, username, logType, LoginResultEnum.SUCCESS);
        // 创建访问令牌
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.createAccessToken(userId, getUserType().getValue(),
                OAuth2ClientConstants.CLIENT_ID_PAD, null);
        String token = "Bearer "+accessTokenDO.getAccessToken();
        accessTokenDO.setAccessToken(token);
        // 构建返回结果
        return AuthConvert.INSTANCE.convert(accessTokenDO);
    }

    @Override
    public AuthLoginRespVO refreshToken(String refreshToken) {
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.refreshAccessToken(refreshToken, OAuth2ClientConstants.CLIENT_ID_DEFAULT);
        return AuthConvert.INSTANCE.convert(accessTokenDO);
    }

    @Override
    public void logout(String token, Integer logType) {
        // 删除访问令牌
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.removeAccessToken(token);
        if (accessTokenDO == null) {
            return;
        }
        // 删除成功，则记录登出日志
        createLogoutLog(accessTokenDO.getUserId(), accessTokenDO.getUserType(), logType);
    }

    private void createLogoutLog(Long userId, Integer userType, Integer logType) {
        LoginLogCreateReqDTO reqDTO = new LoginLogCreateReqDTO();
        reqDTO.setLogType(logType);
        reqDTO.setTraceId(TracerUtils.getTraceId());
        reqDTO.setUserId(userId);
        reqDTO.setUserType(userType);
        if (ObjectUtil.equal(getUserType().getValue(), userType)) {
            reqDTO.setUsername(getUsername(userId));
        } else {
            reqDTO.setUsername(memberService.getMemberUserMobile(userId));
        }
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        reqDTO.setUserIp(ServletUtils.getClientIP());
        reqDTO.setResult(LoginResultEnum.SUCCESS.getResult());
        loginLogService.createLoginLog(reqDTO);
    }

    private String getUsername(Long userId) {
        if (userId == null) {
            return null;
        }
        AdminUserDO user = userService.getUser(userId);
        return user != null ? user.getUsername() : null;
    }

    private UserTypeEnum getUserType() {
        return UserTypeEnum.ADMIN;
    }

    public AuthLoginReqVO PadLoginToAuthLogin(PadLoginParams params){
        AuthLoginReqVO authLoginReqVO = new AuthLoginReqVO();
        authLoginReqVO.setUsername(params.getUsername());
        authLoginReqVO.setPassword(params.getPassword());
        authLoginReqVO.setCaptchaVerification(params.getValidCode());
        return authLoginReqVO;
    }

    public AuthSmsLoginReqVO PadLoginToAuthSmsLogin(PadLoginParams params){
        AuthSmsLoginReqVO authLoginReqVO = new AuthSmsLoginReqVO();
        authLoginReqVO.setMobile(params.getPhoneNo());
        authLoginReqVO.setCode(params.getValidCode());
        return authLoginReqVO;
    }

}
