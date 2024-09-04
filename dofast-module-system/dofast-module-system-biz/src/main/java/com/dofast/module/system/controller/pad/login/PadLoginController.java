package com.dofast.module.system.controller.pad.login;

import cn.hutool.core.util.StrUtil;
import com.dofast.framework.common.enums.CommonStatusEnum;
import com.dofast.framework.common.pojo.AjaxResult;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.util.collection.SetUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.framework.security.config.SecurityProperties;
import com.dofast.framework.security.core.annotations.PreAuthenticated;
import com.dofast.framework.tenant.core.aop.TenantIgnore;
import com.dofast.framework.tenant.core.context.TenantContextHolder;
import com.dofast.module.system.controller.admin.auth.vo.*;
import com.dofast.module.system.controller.pad.login.vo.PadLoginParams;
import com.dofast.module.system.controller.pad.login.vo.PadLoginRes;
import com.dofast.module.system.convert.auth.AuthConvert;
import com.dofast.module.system.dal.dataobject.permission.MenuDO;
import com.dofast.module.system.dal.dataobject.permission.RoleDO;
import com.dofast.module.system.dal.dataobject.tenant.TenantDO;
import com.dofast.module.system.dal.dataobject.user.AdminUserDO;
import com.dofast.module.system.enums.logger.LoginLogTypeEnum;
import com.dofast.module.system.enums.permission.MenuTypeEnum;
import com.dofast.module.system.service.auth.AdminPadAuthService;
import com.dofast.module.system.service.permission.PermissionService;
import com.dofast.module.system.service.permission.RoleService;
import com.dofast.module.system.service.social.SocialUserService;
import com.dofast.module.system.service.tenant.TenantService;
import com.dofast.module.system.service.user.AdminUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static com.dofast.framework.security.core.util.SecurityFrameworkUtils.obtainAuthorization;
import static java.util.Collections.singleton;

@Tag(name = "PAD端 - 登录")
@RestController
@RequestMapping("/mobile/login")
@Validated
public class PadLoginController {
    @Resource
    private AdminPadAuthService authService;
    @Resource
    private AdminUserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private SocialUserService socialUserService;
    @Resource
    private SecurityProperties securityProperties;
    @Resource
    private TenantService tenantService;

    /**
     * 会员登录
     * @return -1 用户名或密码错误  -2 账号冻结  -3 账号锁定 1 成功  -4 验证码错误
     */
    @PostMapping("/loginByPassword")
    @ResponseBody
    @TenantIgnore
    @OperateLog(enable = false)
    @PermitAll
    public AjaxResult loginByPassword(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phoneNo = request.getParameter("phoneNo");
        String validCode = request.getParameter("validCode");
        String loginType = request.getParameter("loginType");
        // 登录结果
        PadLoginParams loginParams = new PadLoginParams();
        loginParams.setUsername(username);
        loginParams.setPassword(password);
        loginParams.setPhoneNo(phoneNo);
        loginParams.setValidCode(validCode);
        loginParams.setLoginType(loginType);
        return authService.login(loginParams);
    }

    /*@PostMapping("/login")
    @PermitAll
    @Operation(summary = "使用账号密码登录")
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    @TenantIgnore
    public CommonResult<PadLoginRes> login(@RequestBody PadLoginParams params) {
        return success(authService.login(params));
    }*/

    @GetMapping("/logout")
    @Operation(summary = "登出系统")
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    @TenantIgnore
    @PermitAll
    public AjaxResult logout(HttpServletRequest request) {
        String token = obtainAuthorization(request, securityProperties.getTokenHeader());
        if (StrUtil.isNotBlank(token)) {
            authService.logout(token, LoginLogTypeEnum.LOGOUT_SELF.getType());
        }
        return AjaxResult.success(true);
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "刷新令牌")
    @Parameter(name = "refreshToken", description = "刷新令牌", required = true)
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    @TenantIgnore
    @PermitAll
    public AjaxResult refreshToken(@RequestParam("refreshToken") String refreshToken) {
        return AjaxResult.success(authService.refreshToken(refreshToken));
    }



    @GetMapping("/get-permission-info")
    @Operation(summary = "获取登录用户的权限信息")
    public CommonResult<AuthPermissionInfoRespVO> getPermissionInfo() {
        // 获得用户信息
        AdminUserDO user = userService.getUser(getLoginUserId());
        if (user == null) {
            return null;
        }
        // 获得角色列表
        Set<Long> roleIds = permissionService.getUserRoleIdsFromCache(getLoginUserId(), singleton(CommonStatusEnum.ENABLE.getStatus()));
        List<RoleDO> roleList = roleService.getRoleListFromCache(roleIds);
        // 获得菜单列表
        List<MenuDO> menuList = permissionService.getRoleMenuListFromCache(roleIds,
                SetUtils.asSet(MenuTypeEnum.DIR.getType(), MenuTypeEnum.MENU.getType(), MenuTypeEnum.BUTTON.getType()),
                singleton(CommonStatusEnum.ENABLE.getStatus())); // 只要开启的
        TenantDO info =  tenantService.getTenant(TenantContextHolder.getTenantId());
        // 拼接结果返回
        return success(AuthConvert.INSTANCE.convert(user, roleList, menuList, info));
    }

    @GetMapping("/list-menus")
    @Operation(summary = "获得登录用户的菜单列表")
    public CommonResult<List<AuthMenuRespVO>> getMenuList() {
        // 获得角色列表
        Set<Long> roleIds = permissionService.getUserRoleIdsFromCache(getLoginUserId(), singleton(CommonStatusEnum.ENABLE.getStatus()));
        // 获得用户拥有的菜单列表
        List<MenuDO> menuList = permissionService.getRoleMenuListFromCache(roleIds,
                SetUtils.asSet(MenuTypeEnum.DIR.getType(), MenuTypeEnum.MENU.getType()), // 只要目录和菜单类型
                singleton(CommonStatusEnum.ENABLE.getStatus())); // 只要开启的
        // 转换成 Tree 结构返回
        return success(AuthConvert.INSTANCE.buildMenuTree(menuList));
    }

    // ========== 短信登录相关 ==========

    @PostMapping("/sms-login")
    @PreAuthenticated
    @Operation(summary = "使用短信验证码登录")
    @TenantIgnore
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<PadLoginRes> smsLogin(@RequestBody @Valid PadLoginParams params) {
        return success(authService.smsLogin(params));
    }

    @PostMapping("/send-sms-code")
    @PreAuthenticated
    @Operation(summary = "发送手机验证码")
    @TenantIgnore
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<Boolean> sendLoginSmsCode(@RequestBody @Valid AuthSmsSendReqVO reqVO) {
        authService.sendSmsCode(reqVO);
        return success(true);
    }

    // ========== 社交登录相关 ==========

    @GetMapping("/social-auth-redirect")
    @PreAuthenticated
    @Operation(summary = "社交授权的跳转")
    @TenantIgnore
    @Parameters({
            @Parameter(name = "type", description = "社交类型", required = true),
            @Parameter(name = "redirectUri", description = "回调路径")
    })
    public CommonResult<String> socialLogin(@RequestParam("type") Integer type,
                                                    @RequestParam("redirectUri") String redirectUri) {
        return CommonResult.success(socialUserService.getAuthorizeUrl(type, redirectUri));
    }

}
